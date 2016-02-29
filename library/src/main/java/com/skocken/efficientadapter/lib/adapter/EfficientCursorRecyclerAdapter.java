package com.skocken.efficientadapter.lib.adapter;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.annotation.WorkerThread;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import java.util.Collection;
import java.util.List;

public class EfficientCursorRecyclerAdapter extends EfficientRecyclerAdapter<Cursor> implements Filterable,
        CursorFilter.CursorFilterClient {

    private boolean mDataValid;
    private boolean mAutoRequery;
    private Cursor mCursor;
    private int mRowIDColumn;
    private ChangeObserver mChangeObserver;
    private DataSetObserver mDataSetObserver;
    private CursorFilter mCursorFilter;
    private FilterQueryProvider mFilterQueryProvider;

    /**
     * If set the adapter will call requery() on the cursor whenever a content change
     * notification is delivered. Implies {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     *
     * @deprecated This option is discouraged, as it results in Cursor queries
     * being performed on the application's UI thread and thus can cause poor
     * responsiveness or even Application Not Responding errors.  As an alternative,
     * use {@link android.app.LoaderManager} with a {@link android.content.CursorLoader}.
     */
    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 0x01;
    /**
     * If set the adapter will register a content observer on the cursor and will call
     * {@link #onContentChanged()} when a notification comes in.  Be careful when
     * using this flag: you will need to unset the current Cursor from the adapter
     * to avoid leaks due to its registered observers.  This flag is not needed
     * when using a CursorAdapter with a
     * {@link android.content.CursorLoader}.
     */
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 0x02;

    public EfficientCursorRecyclerAdapter() {
        this(null);
    }

    public EfficientCursorRecyclerAdapter(Cursor cursor) {
        this(0, null, cursor);
    }

    public EfficientCursorRecyclerAdapter(Cursor cursor, int flag) {
        this(0, null, cursor, flag);
    }

    public EfficientCursorRecyclerAdapter(Cursor cursor, boolean autoRequery) {
        this(0, null, cursor, autoRequery ? FLAG_AUTO_REQUERY : FLAG_REGISTER_CONTENT_OBSERVER);
    }

    public EfficientCursorRecyclerAdapter(int layoutResId,
                                          Class<? extends EfficientViewHolder<Cursor>> viewHolderClass,
                                          Cursor cursor) {
        this(layoutResId, viewHolderClass, cursor, FLAG_AUTO_REQUERY);
    }



    public EfficientCursorRecyclerAdapter(int layoutResId,
                                          Class<? extends EfficientViewHolder<Cursor>> viewHolderClass,
                                          Cursor cursor,
                                          boolean autoRequery) {
        this(layoutResId, viewHolderClass, cursor, autoRequery ? FLAG_AUTO_REQUERY : FLAG_REGISTER_CONTENT_OBSERVER);
    }

    public EfficientCursorRecyclerAdapter(int layoutResId,
                                          Class<? extends EfficientViewHolder<Cursor>> viewHolderClass,
                                          Cursor cursor,
                                          int flag) {
        super(layoutResId, viewHolderClass);
        init(cursor, flag);
        setHasStableIds(true);
    }

    void init(Cursor c, int flags) {
        if ((flags & FLAG_AUTO_REQUERY) == FLAG_AUTO_REQUERY) {
            flags |= FLAG_REGISTER_CONTENT_OBSERVER;
            mAutoRequery = true;
        } else {
            mAutoRequery = false;
        }
        boolean cursorPresent = c != null;
        mCursor = c;
        mDataValid = cursorPresent;
        mRowIDColumn = cursorPresent ? c.getColumnIndexOrThrow("_id") : -1;
        if ((flags & FLAG_REGISTER_CONTENT_OBSERVER) == FLAG_REGISTER_CONTENT_OBSERVER) {
            mChangeObserver = new ChangeObserver();
            mDataSetObserver = new MyDataSetObserver();
        } else {
            mChangeObserver = null;
            mDataSetObserver = null;
        }
        if (cursorPresent) {
            if (mChangeObserver != null) c.registerContentObserver(mChangeObserver);
            if (mDataSetObserver != null) c.registerDataSetObserver(mDataSetObserver);
        }
    }

    /**
     * Returns the cursor.
     *
     * @return the cursor.
     */
    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public boolean hasItem(Cursor object) {
        return indexOf(object) > -1;
    }

    @Override
    public int indexOf(Cursor object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(Collection<? extends Cursor> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(Cursor... items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Cursor object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int position, Cursor object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAt(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Cursor object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move(int from, int to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Cursor> getObjects() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getItemId(int position) {
        if (mDataValid && mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                return mCursor.getLong(mRowIDColumn);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Cursor get(int position) {
        if (mDataValid && mCursor != null) {
            mCursor.moveToPosition(position);
            return mCursor;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        if (mDataValid && mCursor != null) {
            return mCursor.getCount();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int getItemCount() {
        return size();
    }

    @Override
    public void onBindViewHolder(EfficientViewHolder viewHolder, int position) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        super.onBindViewHolder(viewHolder, position);
    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     *
     * @param cursor The new cursor to be used
     */
    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * {@link #changeCursor(Cursor)}, the returned old Cursor is <em>not</em>
     * closed.
     *
     * @param newCursor The new cursor to be used.
     *
     * @return Returns the previously set Cursor, or null if there wasa not one.
     * If the given new Cursor is the same instance is the previously set
     * Cursor, null is also returned.
     */
    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        Cursor oldCursor = mCursor;
        if (oldCursor != null) {
            if (mChangeObserver != null) oldCursor.unregisterContentObserver(mChangeObserver);
            if (mDataSetObserver != null) oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (newCursor != null) {
            if (mChangeObserver != null) newCursor.registerContentObserver(mChangeObserver);
            if (mDataSetObserver != null) newCursor.registerDataSetObserver(mDataSetObserver);
            mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
            mDataValid = true;
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            mRowIDColumn = -1;
            mDataValid = false;
            // notify the observers about the lack of a data set
            // No notifyDataSetInvalidated() method in RecyclerView.Adapter
            notifyDataSetChanged();
        }
        return oldCursor;
    }

    /**
     * <p>Converts the cursor into a CharSequence. Subclasses should override this
     * method to convert their results. The default implementation returns an
     * empty String for null values or the default String representation of
     * the value.</p>
     *
     * @param cursor the cursor to convert to a CharSequence
     * @return a CharSequence representing the value
     */
    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    /**
     * Runs a query with the specified constraint. This query is requested
     * by the filter attached to this adapter.
     *
     * The query is provided by a
     * {@link android.widget.FilterQueryProvider}.
     * If no provider is specified, the current cursor is not filtered and returned.
     *
     * After this method returns the resulting cursor is passed to {@link #changeCursor(Cursor)}
     * and the previous cursor is closed.
     *
     * This method is always executed on a background thread, not on the
     * application's main thread (or UI thread.)
     *
     * Contract: when constraint is null or empty, the original results,
     * prior to any filtering, must be returned.
     *
     * @param constraint the constraint with which the query must be filtered
     *
     * @return a Cursor representing the results of the new query
     *
     * @see #getFilter()
     * @see #getFilterQueryProvider()
     * @see #setFilterQueryProvider(android.widget.FilterQueryProvider)
     */
    @WorkerThread
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        if (mFilterQueryProvider != null) {
            return mFilterQueryProvider.runQuery(constraint);
        }
        return mCursor;
    }

    public Filter getFilter() {
        if (mCursorFilter == null) {
            mCursorFilter = new CursorFilter(this);
        }
        return mCursorFilter;
    }

    /**
     * Returns the query filter provider used for filtering. When the
     * provider is null, no filtering occurs.
     *
     * @return the current filter query provider or null if it does not exist
     *
     * @see #setFilterQueryProvider(android.widget.FilterQueryProvider)
     * @see #runQueryOnBackgroundThread(CharSequence)
     */
    public FilterQueryProvider getFilterQueryProvider() {
        return mFilterQueryProvider;
    }

    /**
     * Sets the query filter provider used to filter the current Cursor.
     * The provider's
     * {@link android.widget.FilterQueryProvider#runQuery(CharSequence)}
     * method is invoked when filtering is requested by a client of
     * this adapter.
     *
     * @param filterQueryProvider the filter query provider or null to remove it
     *
     * @see #getFilterQueryProvider()
     * @see #runQueryOnBackgroundThread(CharSequence)
     */
    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        mFilterQueryProvider = filterQueryProvider;
    }

    /**
     * Called when the {@link ContentObserver} on the cursor receives a change notification.
     * The default implementation provides the auto-requery logic, but may be overridden by
     * sub classes.
     *
     * @see ContentObserver#onChange(boolean)
     */
    private void onContentChanged() {
        if (mAutoRequery && mCursor != null && !mCursor.isClosed()) {
            //            if (false) Log.v("Cursor", "Auto requerying " + mCursor + " due to update");
            mDataValid = mCursor.requery();
        }
    }

    private class ChangeObserver extends ContentObserver {
        public ChangeObserver() {
            super(new Handler());
        }

        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            onContentChanged();
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            mDataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            mDataValid = false;
            // No notifyDataSetInvalidated() method in RecyclerView.Adapter
            notifyDataSetChanged();
        }
    }

}
