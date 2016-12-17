package com.skocken.efficientadapter.lib.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EfficientPagerAdapter<T> extends PagerAdapter implements EfficientAdapter<T> {

    private final AdapterHelper<T> mBaseAdapter;
    private SparseArray<List<EfficientViewHolder<T>>> mRecycleViewHolders = new SparseArray<>();

    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public EfficientPagerAdapter(T... objects) {
        this(new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public EfficientPagerAdapter(List<T> objects) {
        this(0, null, objects);
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    public EfficientPagerAdapter(int layoutResId,
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
    public EfficientPagerAdapter(int layoutResId,
            Class<? extends EfficientViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        mBaseAdapter = new AdapterHelper<>(layoutResId, viewHolderClass, objects);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mBaseAdapter.setOnItemClickListener(listener);
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mBaseAdapter.setOnItemLongClickListener(listener);
    }

    @Override
    public OnItemClickListener<T> getOnItemClickListener() {
        return mBaseAdapter.getOnItemClickListener();
    }

    @Override
    public OnItemLongClickListener<T> getOnItemLongClickListener() {
        return mBaseAdapter.getOnItemLongClickListener();
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
        mBaseAdapter.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(T... items) {
        mBaseAdapter.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void add(T object) {
        mBaseAdapter.add(object);
        notifyDataSetChanged();
    }

    @Override
    public void add(int position, T object) {
        mBaseAdapter.add(position, object);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int position) {
        mBaseAdapter.removeAt(position);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T object) {
        mBaseAdapter.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public void move(int from, int to) {
        mBaseAdapter.move(from, to);
        notifyDataSetChanged();
    }

    @Override
    public void updateWith(List<T> list) {
        mBaseAdapter.updateWith(this, list);
    }

    @Override
    public void clear() {
        mBaseAdapter.clear();
        notifyDataSetChanged();
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
    public void notifyItemChanged(int i) {
        notifyDataSetChanged();
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
        mBaseAdapter.onBindViewHolder(viewHolder, position, this);
    }

    @Override
    public void onViewRecycled(EfficientViewHolder holder) {
        holder.onViewRecycled();
    }

    @Override
    public void onViewAttachedToWindow(EfficientViewHolder holder) {
        holder.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(EfficientViewHolder holder) {
        holder.onViewDetachedFromWindow();
    }

    @Override
    public EfficientViewHolder<T> generateViewHolder(View v, int viewType) {
        Class<? extends EfficientViewHolder<? extends T>> viewHolderClass = getViewHolderClass(viewType);
        if (viewHolderClass == null) {
            mBaseAdapter.throwMissingViewHolder(viewType);
            return null;
        }
        return mBaseAdapter.generateViewHolder(v, viewHolderClass, this);
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

    @Override
    public int getCount() {
        return size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((EfficientViewHolder) object).getView();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object object) {
        EfficientViewHolder<T> viewHolder = (EfficientViewHolder<T>) object;
        collection.removeView(viewHolder.getView());
        onViewDetachedFromWindow(viewHolder);

        int viewType = getItemViewType(position);
        List<EfficientViewHolder<T>> viewHolders = mRecycleViewHolders.get(viewType);
        if (viewHolders == null) {
            viewHolders = new ArrayList<>();
        }
        mRecycleViewHolders.put(viewType, viewHolders);
        viewHolders.add(viewHolder);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        int viewType = getItemViewType(position);
        List<EfficientViewHolder<T>> viewHolders = mRecycleViewHolders.get(viewType);
        EfficientViewHolder viewHolder;
        if (viewHolders == null || viewHolders.isEmpty()) {
            viewHolder = onCreateViewHolder(collection, viewType);
        } else {
            viewHolder = viewHolders.remove(0);
            onViewRecycled(viewHolder);
        }
        onBindViewHolder(viewHolder, position);
        collection.addView(viewHolder.getView(), 0);
        onViewAttachedToWindow(viewHolder);
        return viewHolder;
    }
}
