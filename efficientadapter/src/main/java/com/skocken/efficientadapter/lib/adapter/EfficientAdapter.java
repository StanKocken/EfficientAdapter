package com.skocken.efficientadapter.lib.adapter;

import com.skocken.efficientadapter.lib.util.AdapterUpdater;
import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

public interface EfficientAdapter<T> extends AdapterUpdater.Updater<T> {

    /**
     * Register a callback to be invoked when an item in this adapter has been clicked.
     * An {@link EfficientViewHolder} can disable the click on it by overriding the method
     * {@link EfficientViewHolder#isLongClickable()} ()}
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemClickListener(EfficientAdapter.OnItemClickListener<T> listener);

    /**
     * Register a callback to be invoked when an item in this adapter has been long-clicked.
     * An {@link EfficientViewHolder} can disable the long-click on it by overriding the method
     * {@link EfficientViewHolder#isClickable()}
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemLongClickListener(EfficientAdapter.OnItemLongClickListener<T> listener);

    /**
     * Get the callback to be invoked when an item in this adapter has been clicked.
     * @return listener The callback that will be invoked on item click.
     */
    EfficientAdapter.OnItemClickListener<T> getOnItemClickListener();

    /**
     * Get the callback to be invoked when an item in this adapter has been long-clicked.
     * @return listener The callback that will be invoked on item long click.
     */
    EfficientAdapter.OnItemLongClickListener<T> getOnItemLongClickListener();

    /**
     * Returns whether this {@code Adapter} contains this element.
     *
     * @param object the object to search for.
     * @return {@code true} if this {@code Adapter} has this elements, {@code false}
     * otherwise.
     */
    boolean hasItem(T object);

    /**
     * Searches this {@code Adapter} for the specified object and returns the index of the
     * first occurrence.
     *
     * @param object the object to search for.
     * @return the index of the first occurrence of the object or -1 if the
     * object was not found.
     */
    int indexOf(T object);

    /**
     * Adds the objects in the specified collection to the end of this {@code Adapter}. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param collection the collection of objects.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    void addAll(Collection<? extends T> collection);

    /**
     * Adds the objects in the specified varargs to the end of this {@code Adapter}. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param items the varargs of objects.
     */
    void addAll(T... items);

    /**
     * Adds the specified object at the end of this {@code Adapter}.
     *
     * @param object the object to add.
     */
    void add(T object);

    /**
     * Inserts the specified object into this {@code Adapter} at the specified
     * location. The object is inserted before any previous element at the
     * specified location. If the location is equal to the size of this
     * {@code Adapter}, the object is added at the end.
     *
     * @param index  the index at which to insert the object.
     * @param object the object to add.
     * @throws IndexOutOfBoundsException when {@code location < 0 || location > size()}
     */
    void add(int index, T object);

    /**
     * Removes the object at the specified location from this {@code Adapter}.
     *
     * @param index the index of the object to remove.
     * @throws IndexOutOfBoundsException when {@code location < 0 || location >= size()}
     */
    void removeAt(int index);

    /**
     * Removes the first occurrence of the specified object from this {@code Adapter}.
     *
     * @param object the object to remove.
     */
    void remove(T object);

    /**
     * Move the object at the index {@code from} to the index {@code to}
     */
    void move(int from, int to);

    /**
     * Update the adapter list with this new list.
     * Using this method, instead of clear/addAll will allow the implementation to compute the best way to update the
     * elements.
     * For example, if you have only one item which was in the previous list and which is not on the new, the
     * Updater has an opportunity to just call `remove` on this item.
     * @param list the new list of item to be into this adapter.
     */
    void updateWith(List<T> list);

    /**
     * Removes all elements from this {@code Adapter}, leaving it empty.
     *
     * @see #isEmpty
     * @see #size
     */
    void clear();

    /**
     * Returns whether this {@code Adapter} contains no elements.
     *
     * @return {@code true} if this {@code Adapter} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this {@code Adapter}.
     *
     * @return the number of elements in this {@code Adapter}.
     */
    int size();

    /**
     * Get a copy of the list of objects contain into this adapter
     */
    List<T> getObjects();

    /**
     * Returns the element at the specified location in this {@code Adapter}.
     *
     * @param location the index of the element to return.
     * @return the element at the specified location.
     * @throws IndexOutOfBoundsException if {@code location < 0 || location >= size()}
     */
    T get(int location);

    /**
     * Called when view needs a new {@link EfficientViewHolder} of the given type to represent
     * an item.
     * <p>The default implementation of this method will call {@link #generateView(ViewGroup,
     * int)} to generate the view from the viewType, then {@link #generateViewHolder(View, int)}
     * to create the {@link EfficientViewHolder} related.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(EfficientViewHolder, int)}.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new {@link EfficientViewHolder} that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(EfficientViewHolder, int)
     */
    EfficientViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     *
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.</p>
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    int getItemViewType(int position);

    /**
     * Called by the view to display the data at the specified position.
     *
     * <p>The default implementation of this method will call {@link EfficientViewHolder#onBindView(Object,
     * int)}
     * on the {@link EfficientViewHolder} and set the click listeners if necessary.</p>
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *                   item at the given position in the data set.
     * @param position   The position of the item within the adapter's data set.
     */
    void onBindViewHolder(EfficientViewHolder<T> viewHolder, int position);

    /**
     * Called when the {@link EfficientViewHolder} has been recycled
     */
    void onViewRecycled(EfficientViewHolder<T> holder);

    /**
     * Called when the {@link EfficientViewHolder} has been attached to window
     */
    void onViewAttachedToWindow(EfficientViewHolder<T> holder);

    /**
     * Called when the {@link EfficientViewHolder} has been detached to window
     */
    void onViewDetachedFromWindow(EfficientViewHolder<T> holder);

    /**
     * Called by {@link #onCreateViewHolder(ViewGroup, int)}  when the adapter need to generate an
     * {@link EfficientViewHolder} for a particular viewType
     *
     * @param v        The root view of this {@link EfficientViewHolder}
     * @param viewType The view type of the new View.
     * @return a new {@link EfficientViewHolder}
     */
    EfficientViewHolder<T> generateViewHolder(View v, int viewType);

    /**
     * Called by {@link #onCreateViewHolder(ViewGroup, int)} when the adapter need to generate a
     * {@link View} for a particular viewType.
     *
     * <p>The default implementation of this method is to call {@link #getLayoutResId(int)}</p>
     *
     * @param parent   The parent view of this new view
     * @param viewType The view type of the new View.
     * @return a new View
     */
    View generateView(ViewGroup parent, int viewType);

    /**
     * Called by {@link #generateView(ViewGroup, int)} to indicate which layout need to be
     * inflate for this viewType
     *
     * @param viewType The view type of the new View.
     * @return The layout resource id to inflate.
     */
    @LayoutRes
    int getLayoutResId(int viewType);

    /**
     * Called by {@link #generateViewHolder(View, int)} to indicate which {@link
     * EfficientViewHolder} need to be
     * inflate for this viewType
     *
     * @param viewType The view type of the new View.
     * @return The class of the {@link EfficientViewHolder} to instantiate
     */
    Class<? extends EfficientViewHolder<? extends T>> getViewHolderClass(int viewType);

    /**
     * Interface definition for a callback to be invoked when an item in this
     * {@link EfficientViewHolder} has been clicked.
     */
    interface OnItemClickListener<T> {

        /**
         * Callback method to be invoked when an item in this {@link EfficientViewHolder} has
         * been clicked.
         *
         * @param adapter  The EfficientAdapter where the click happened.
         * @param view     The view within the EfficientAdapter that was clicked (this
         *                 will be a view provided by the adapter)
         * @param object   The object associate with this {@link EfficientViewHolder}
         * @param position The position of the view in the adapter.
         */
        void onItemClick(EfficientAdapter<T> adapter, View view, T object, int position);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * {@link EfficientViewHolder} has been clicked and held.
     */
    interface OnItemLongClickListener<T> {

        /**
         * Callback method to be invoked when an item in this {@link EfficientViewHolder} has
         * been clicked and held.
         *
         * @param adapter  The EfficientAdapter where the click happened.
         * @param view     The view within the EfficientAdapter that was clicked (this
         *                 will be a view provided by the adapter)
         * @param object   The object associate with this {@link EfficientViewHolder}
         * @param position The position of the view in the adapter.
         */
        void onLongItemClick(EfficientAdapter<T> adapter, View view, T object, int position);
    }
}
