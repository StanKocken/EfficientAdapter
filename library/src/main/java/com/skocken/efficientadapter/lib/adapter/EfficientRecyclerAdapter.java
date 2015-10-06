package com.skocken.efficientadapter.lib.adapter;

import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EfficientRecyclerAdapter<T> extends RecyclerView.Adapter<EfficientViewHolder>
        implements EfficientAdapter<T> {

    private final AdapterHelper<T> mBaseAdapter;

    private boolean mNotifyOnChange = true;

    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public EfficientRecyclerAdapter(T... objects) {
        this(new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public EfficientRecyclerAdapter(List<T> objects) {
        this(0, null, objects);
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    public EfficientRecyclerAdapter(int layoutResId,
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
    public EfficientRecyclerAdapter(int layoutResId,
            Class<? extends EfficientViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        mBaseAdapter = new AdapterHelper<>(layoutResId, viewHolderClass, objects);
    }

    public void setNotifyOnChange(boolean enable) {
        mNotifyOnChange = enable;
    }

    @Override
    public void setOnItemClickListener(EfficientAdapter.OnItemClickListener<T> listener) {
        mBaseAdapter.setOnItemClickListener(listener);
    }

    @Override
    public void setOnItemLongClickListener(EfficientAdapter.OnItemLongClickListener<T> listener) {
        mBaseAdapter.setOnItemLongClickListener(listener);
    }

    @Override
    public boolean hasItem(T object) {
        return mBaseAdapter.hasItem(object);
    }

    @Override
    public int indexOf(T object) {
        return mBaseAdapter.indexOf(object);
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        int positionOfInsert = mBaseAdapter.addAll(collection);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void addAll(T... items) {
        int positionOfInsert = mBaseAdapter.addAll(items);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void add(T object) {
        int positionOfInsert = mBaseAdapter.add(object);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void add(int position, T object) {
        mBaseAdapter.add(position, object);
        if (mNotifyOnChange) {
            notifyItemInserted(position);
        }
    }

    @Override
    public void removeAt(int position) {
        T removeAt = mBaseAdapter.removeAt(position);
        if (mNotifyOnChange && removeAt != null) {
            notifyItemRemoved(position);
        }
    }

    @Override
    public void remove(T object) {
        int positionOfRemove = mBaseAdapter.remove(object);
        if (mNotifyOnChange && positionOfRemove >= 0) {
            notifyItemRemoved(positionOfRemove);
        }
    }

    @Override
    public void move(int from, int to) {
        mBaseAdapter.move(from, to);
        notifyItemMoved(from, to);
    }

    @Override
    public void clear() {
        int nbObjectRemoved = mBaseAdapter.clear();
        if (mNotifyOnChange) {
            for (int i = nbObjectRemoved - 1; i >= 0; i--) {
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return mBaseAdapter.isEmpty();
    }

    @Override
    public int size() {
        return mBaseAdapter.size();
    }

    @Override
    public List<T> getObjects() {
        return mBaseAdapter.getObjects();
    }

    @Override
    public T get(int position) {
        return mBaseAdapter.get(position);
    }

    @Override
    public EfficientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = generateView(parent, viewType);
        return generateViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(EfficientViewHolder viewHolder, int position) {
        T object = mBaseAdapter.get(position);
        viewHolder.onBindView(object, position);

        mBaseAdapter.setClickListenerOnView(this, viewHolder);
        mBaseAdapter.setLongClickListenerOnView(this, viewHolder);
    }

    @Override
    public void onViewRecycled(EfficientViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onViewRecycled();
    }

    @Override
    public void onViewAttachedToWindow(EfficientViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(EfficientViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    @Override
    public int getItemCount() {
        return size();
    }

    @Override
    public EfficientViewHolder generateViewHolder(View v, int viewType) {
        Class<? extends EfficientViewHolder> viewHolderClass = getViewHolderClass(viewType);
        if (viewHolderClass == null) {
            mBaseAdapter.throwMissingViewHolder(viewType);
            return null;
        }
        return mBaseAdapter.generateViewHolder(v, viewHolderClass);
    }

    @Override
    public View generateView(ViewGroup parent, int viewType) {
        int layoutResId = getLayoutResId(viewType);
        if (layoutResId == 0) {
            mBaseAdapter.throwMissingLayoutResId(viewType);
            return null;
        }
        return mBaseAdapter.inflateView(parent, layoutResId);
    }

    @Override
    public int getLayoutResId(int viewType) {
        // default implementation doesn't depend on 'viewType', we take the layout res id from
        // the constructor
        return mBaseAdapter.getLayoutResId();
    }

    @Override
    public Class<? extends EfficientViewHolder<? extends T>> getViewHolderClass(int viewType) {
        // default implementation doesn't depend on 'viewType', we take the viewholder class from
        // the constructor
        return mBaseAdapter.getViewHolderClass();
    }
}
