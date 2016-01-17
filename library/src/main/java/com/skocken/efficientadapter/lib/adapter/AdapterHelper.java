package com.skocken.efficientadapter.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class AdapterHelper<T> {

    private final Class<? extends EfficientViewHolder<? extends T>> mViewHolderClass;

    private final int mLayoutResId;

    private final List<T> mObjects;

    private EfficientAdapter.OnItemClickListener<T> mOnItemClickListener;

    private EfficientAdapter.OnItemLongClickListener<T> mOnItemLongClickListener;

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    AdapterHelper(T... objects) {
        this(new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    AdapterHelper(List<T> objects) {
        this(0, null, objects);
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    AdapterHelper(int layoutResId,
                  Class<? extends EfficientViewHolder<? extends T>> viewHolderClass, T... objects) {
        this(layoutResId, viewHolderClass, new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    AdapterHelper(int layoutResId,
                  Class<? extends EfficientViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        if (objects == null) {
            mObjects = new ArrayList<>();
        } else {
            mObjects = objects;
        }
        mViewHolderClass = viewHolderClass;
        mLayoutResId = layoutResId;
    }

    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been long-clicked.
     * <p/>
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemLongClickListener(EfficientAdapter.OnItemLongClickListener<T> listener) {
        mOnItemLongClickListener = listener;
    }

    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been clicked.
     * <p/>
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemClickListener(EfficientAdapter.OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    /**
     * Determine if the object provide is in this adapter
     *
     * @return true if the object is in this adapter
     */
    boolean hasItem(T object) {
        return mObjects.contains(object);
    }

    /**
     * Searches this {@code List} for the specified object and returns the index of the
     * first occurrence.
     *
     * @param object the object to search for.
     * @return the index of the first occurrence of the object or -1 if the
     * object was not found.
     */
    int indexOf(T object) {
        return mObjects.indexOf(object);
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    int addAll(Collection<? extends T> collection) {
        int positionOfInsert = mObjects.size();
        mObjects.addAll(collection);
        return positionOfInsert;
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    int addAll(T... items) {
        int positionOfInsert = mObjects.size();
        Collections.addAll(mObjects, items);
        return positionOfInsert;
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    int add(T object) {
        int positionOfInsert = mObjects.size();
        mObjects.add(object);
        return positionOfInsert;
    }

    /**
     * Adds the specified object at the specified position of the array.
     *
     * @param position The position of object to add
     * @param object   The object to add at the end of the array.
     */
    int add(int position, T object) {
        mObjects.add(position, object);
        return position;
    }


    /**
     * Remove the object at the specified position of the array.
     *
     * @param position The position of object to add
     */
    T removeAt(int position) {
        return mObjects.remove(position);
    }


    /**
     * Remove the specified object of the array.
     *
     * @param object The object to add at the end of the array.
     */
    int remove(T object) {
        int positionOfRemove = mObjects.indexOf(object);
        if (positionOfRemove >= 0) {
            T objectRemoved = removeAt(positionOfRemove);
            if (objectRemoved != null) {
                return positionOfRemove;
            }
        }
        return -1;
    }

    /**
     * Move object
     */
    void move(int from, int to) {
        mObjects.add(to, mObjects.remove(from));
    }

    /**
     * Remove all elements from the list.
     */
    int clear() {
        int nbObjectRemoved = mObjects.size();
        mObjects.clear();
        return nbObjectRemoved;
    }

    /**
     * Returns whether this {@code List} contains no elements.
     *
     * @return {@code true} if this {@code List} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this {@code List}.
     *
     * @return the number of elements in this {@code List}.
     */
    int size() {
        return mObjects.size();
    }

    /**
     * @return a copy of the {@code List} of elements.
     */
    List<T> getObjects() {
        return new ArrayList<>(mObjects);
    }

    T get(int position) {
        return mObjects.get(position);
    }

    View inflateView(ViewGroup parent, int layoutResId) {
        if (parent == null) {
            return null;
        }
        Context context = parent.getContext();
        if (context == null) {
            return null;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(layoutResId, parent, false);
    }

    int getLayoutResId() {
        return mLayoutResId;
    }

    Class<? extends EfficientViewHolder<? extends T>> getViewHolderClass() {
        return mViewHolderClass;
    }

    void setClickListenerOnView(final EfficientAdapter<T> efficientAdapter,
                                final EfficientViewHolder viewHolder) {
        View view = viewHolder.getView();
        boolean clickable = viewHolder.isClickable() && mOnItemClickListener != null;
        if (clickable) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem(efficientAdapter, viewHolder);
                }
            });
        } else {
            view.setOnClickListener(null);
            view.setClickable(false);
        }
    }

    void setLongClickListenerOnView(final EfficientAdapter<T> efficientAdapter,
                                    final EfficientViewHolder viewHolder) {
        View view = viewHolder.getView();
        boolean longClickable = viewHolder.isLongClickable() && mOnItemLongClickListener != null;
        if (longClickable) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClickItem(efficientAdapter, viewHolder);
                    return true;
                }
            });
        } else {
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
        }
    }

    <T extends EfficientViewHolder> EfficientViewHolder generateViewHolder(View v,
                                                                           Class<T> viewHolderClass, EfficientAdapter adapter) {
        Constructor<?>[] constructors = viewHolderClass.getDeclaredConstructors();

        if (constructors == null) {
            throw new IllegalArgumentException(
                    "Impossible to found a constructor with a view for "
                            + viewHolderClass.getSimpleName());
        }

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes == null) continue;

            try {
                //single or static inner class ViewHolder
                if (parameterTypes.length == 1 && View.class.isAssignableFrom(parameterTypes[0])) {
                    Object viewHolder = constructor.newInstance(v);
                    return (EfficientViewHolder) viewHolder;
                }

                // inner class ViewHolder inside EfficientAdapter
                boolean isViewHolderInAdapter = parameterTypes.length == 2 && EfficientAdapter.class.isAssignableFrom(parameterTypes[0]) && View.class.isAssignableFrom(parameterTypes[1]);

                if (isViewHolderInAdapter) {
                    Object viewHolder = constructor.newInstance(adapter, v);
                    return (EfficientViewHolder) viewHolder;
                }
            } catch (Exception e) {
                throw new RuntimeException(
                        "Impossible to instantiate "
                                + viewHolderClass.getSimpleName(), e);
            }
        }

        throw new IllegalArgumentException(
                "Impossible to found a constructor with a view for "
                        + viewHolderClass.getSimpleName());
    }

    void throwMissingLayoutResId(int viewType) {
        throw new IllegalArgumentException("No default layout found for the view type '"
                + viewType + "'.");
    }

    void throwMissingViewHolder(int viewType) {
        throw new IllegalArgumentException(
                "You must supply a view holder class for the element for view type "
                        + viewType);
    }

    private void onClickItem(EfficientAdapter<T> efficientAdapter,
                             EfficientViewHolder viewHolder) {
        if (mOnItemClickListener != null) {
            T object = (T) viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            mOnItemClickListener.onItemClick(efficientAdapter, view, object, position);
        }
    }

    private void onLongClickItem(EfficientAdapter<T> efficientAdapter,
                                 EfficientViewHolder viewHolder) {
        if (mOnItemLongClickListener != null) {
            T object = (T) viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            mOnItemLongClickListener.onLongItemClick(efficientAdapter, view, object, position);
        }
    }
}