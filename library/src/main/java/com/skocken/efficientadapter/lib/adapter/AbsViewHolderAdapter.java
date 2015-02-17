package com.skocken.efficientadapter.lib.adapter;

import com.skocken.efficientadapter.lib.viewholder.AbsViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Common adapter to use AbsViewHolder into a RecyclerView
 *
 * @param <T> the king of object into this adapter
 */
public abstract class AbsViewHolderAdapter<T> extends RecyclerView.Adapter<AbsViewHolder> {

    /**
     * Contains the list of objects that represent the data of this AbsViewHolderAdapter.
     * The content of this list is referred to as "the array" in the documentation.
     */
    private List<T> mObjects;

    /**
     * Lock used to modify the content of {@link #mObjects}. Any write operation
     * performed on the array should be synchronized on this lock.
     */
    private final Object mLock = new Object();

    /**
     * The listener that receives notifications when an item is clicked.
     */
    private OnItemClickListener<T> mOnItemClickListener;

    /**
     * The listener that receives notifications when an item is long clicked.
     */
    private OnItemLongClickListener<T> mOnItemLongClickListener;

    /**
     * Describe if we need to notify the adapter on add, addAll, remove or not.
     */
    private boolean mNotifyOnChange = true;

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    public AbsViewHolderAdapter(T... objects) {
        this(new ArrayList<T>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects The objects to represent in the ListView.
     */
    public AbsViewHolderAdapter(List<T> objects) {
        if (objects == null) {
            objects = new ArrayList<T>();
        }
        mObjects = objects;
    }

    /**
     * Get the view holder to instantiate for the object for this position
     *
     * @param viewType viewType return by getItemViewType()
     * @return the class of the view holder to instantiate
     */
    protected abstract Class<? extends AbsViewHolder> getViewHolderClass(int viewType);

    /**
     * The layout to inflate for the object for this viewType
     *
     * @param viewType viewType return by getItemViewType()
     * @return the resource ID of a layout to inflate
     */
    protected abstract int getLayoutResId(int viewType);

    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been long-clicked.
     *
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mOnItemLongClickListener = listener;
    }

    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been clicked.
     *
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    /**
     * Set if we need to notify the adapter on add, addAll or remove object or not.
     */
    public void setNotifyOnChange(boolean enable) {
        mNotifyOnChange = enable;
    }

    /**
     * Determine if the object provide is in this adapter
     *
     * @return true if the object is in this adapter
     */
    public boolean hasItem(T object) {
        synchronized (mLock) {
            return mObjects.contains(object);
        }
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void addAll(Collection<? extends T> collection) {
        int positionOfInsert;
        synchronized (mLock) {
            positionOfInsert = mObjects.size();
            mObjects.addAll(collection);
        }
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    public void addAll(T... items) {
        addAll(Arrays.asList(items));
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void add(T object) {
        int positionOfInsert;
        synchronized (mLock) {
            positionOfInsert = mObjects.size();
            mObjects.add(object);
        }
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    /**
     * Adds the specified object at the specified position of the array.
     *
     * @param position The position of object to add
     * @param object   The object to add at the end of the array.
     */
    public void add(int position, T object) {
        synchronized (mLock) {
            mObjects.add(position, object);
        }
        if (mNotifyOnChange) {
            notifyItemInserted(position);
        }
    }


    /**
     * Remove the object at the specified position of the array.
     *
     * @param position The position of object to add
     */
    public void remove(int position) {
        synchronized (mLock) {
            mObjects.remove(position);
        }
        if (mNotifyOnChange) {
            notifyItemRemoved(position);
        }
    }


    /**
     * Remove the specified object of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void remove(T object) {
        int positionOfRemove;
        synchronized (mLock) {
            positionOfRemove = mObjects.indexOf(object);
        }
        if (positionOfRemove >= 0) {
            remove(positionOfRemove);
        }
    }

    /**
     * Move object
     */
    public void moved(int from, int to) {
        synchronized (mLock) {
            mObjects.add(to, mObjects.remove(from));
        }
        notifyItemMoved(from, to);
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        int nbObjectRemoved;
        synchronized (mLock) {
            nbObjectRemoved = mObjects.size();
            mObjects.clear();
        }
        if (mNotifyOnChange) {
            for (int i = nbObjectRemoved - 1; i >= 0; i--) {
                notifyItemRemoved(i);
            }
        }
    }

    /**
     * Returns whether this {@code List} contains no elements.
     *
     * @return {@code true} if this {@code List} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this {@code List}.
     *
     * @return the number of elements in this {@code List}.
     */
    public int size() {
        synchronized (mLock) {
            return mObjects.size();
        }
    }

    /**
     * @return a copy of the {@code List} of elements.
     */
    public List<T> getObjects() {
        synchronized (mLock) {
            return new ArrayList<T>(mObjects);
        }
    }

    /**
     * Get the object at the position from the array.
     *
     * @param position position of the object in this adapter
     * @return the object found at this position
     */
    public T get(int position) {
        synchronized (mLock) {
            return mObjects.get(position);
        }
    }

    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = generateView(parent, viewType);
        return generateViewHolder(v, viewType);
    }


    @Override
    public void onBindViewHolder(AbsViewHolder viewHolder, int position) {
        T object;
        synchronized (mLock) {
            object = mObjects.get(position);
        }
        viewHolder.onBindView(object);

        boolean isClickable = viewHolder.isClickable() && mOnItemClickListener != null;
        setClickListenerOnView(viewHolder, isClickable);

        boolean isLongClickable = viewHolder.isLongClickable() && mOnItemLongClickListener != null;
        setLongClickListenerOnView(viewHolder, isLongClickable);
    }

    protected void setClickListenerOnView(final AbsViewHolder viewHolder, boolean clickable) {
        View view = viewHolder.getView();
        if (clickable != view.isClickable()) {
            if (clickable) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(viewHolder, viewHolder.getPosition());
                    }
                });
            } else {
                view.setOnClickListener(null);
                view.setClickable(false);
            }
        }
    }

    protected void setLongClickListenerOnView(final AbsViewHolder viewHolder,
            boolean longClickable) {
        View view = viewHolder.getView();
        if (longClickable != view.isLongClickable()) {
            if (longClickable) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onLongClickItem(viewHolder, viewHolder.getPosition());
                        return true;
                    }
                });
            } else {
                view.setOnLongClickListener(null);
                view.setLongClickable(false);
            }
        }
    }

    @Override
    public void onViewRecycled(AbsViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onViewRecycled();
    }

    @Override
    public void onViewAttachedToWindow(AbsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(AbsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    @Override
    public int getItemCount() {
        synchronized (mLock) {
            return (mObjects == null) ? 0 : mObjects.size();
        }
    }

    /**
     * Generate a view to be used into the view holder
     */
    protected View generateView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext())
                .inflate(getLayoutResId(viewType), parent, false);
    }

    /**
     * Generate a view holder for this view for this viewType
     */
    private AbsViewHolder generateViewHolder(View v, int viewType) {
        Class<? extends AbsViewHolder> viewHolderClass = getViewHolderClass(viewType);
        if (viewHolderClass == null) {
            throw new NullPointerException(
                    "You must supply a view holder class for the element for view type "
                            + viewType);
        }
        Constructor<?> constructorWithView = getConstructorWithView(viewHolderClass);
        try {
            Object viewHolder = constructorWithView.newInstance(v);
            return (AbsViewHolder) viewHolder;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Impossible to instantiate "
                            + viewHolderClass.getSimpleName(), e);
        } catch (InstantiationException e) {
            throw new RuntimeException(
                    "Impossible to instantiate "
                            + viewHolderClass.getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Impossible to instantiate "
                            + viewHolderClass.getSimpleName(), e);
        }
    }

    /**
     * Get the constructor with a view for this class
     */
    private Constructor<?> getConstructorWithView(Class<? extends AbsViewHolder> viewHolderClass) {
        Constructor<?>[] constructors = viewHolderClass.getDeclaredConstructors();
        if (constructors != null) {
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes != null
                        && parameterTypes.length == 1
                        && parameterTypes[0].isAssignableFrom(View.class)) {
                    return constructor;
                }
            }
        }
        throw new RuntimeException(
                "Impossible to found a constructor with a view for "
                        + viewHolderClass.getSimpleName());
    }

    private void onClickItem(AbsViewHolder viewHolder, int position) {
        if (mOnItemClickListener != null) {
            View view = viewHolder.getView();
            T object = get(position);
            mOnItemClickListener.onItemClick(this, view, object, position);
        }
    }

    private void onLongClickItem(AbsViewHolder viewHolder, int position) {
        if (mOnItemLongClickListener != null) {
            View view = viewHolder.getView();
            T object = get(position);
            mOnItemLongClickListener.onLongItemClick(this, view, object, position);
        }
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * AbsViewHolderAdapter has been clicked.
     */
    public interface OnItemClickListener<T> {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param parent   The AdapterView where the click happened.
         * @param view     The view within the AdapterView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param object   The object of the view.
         * @param position The position of the view in the adapter.
         */
        void onItemClick(AbsViewHolderAdapter<T> parent, View view, T object, int position);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * AbsViewHolderAdapter has been long-clicked.
     */
    public interface OnItemLongClickListener<T> {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been long-clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param parent   The AdapterView where the click happened.
         * @param view     The view within the AdapterView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param object   The object of the view.
         * @param position The position of the view in the adapter.
         */
        void onLongItemClick(AbsViewHolderAdapter<T> parent, View view, T object,
                int position);
    }
}
