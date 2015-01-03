package com.skocken.efficientadapter.lib.adapter;

import com.skocken.efficientadapter.lib.viewholder.AbsViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A concrete AbsViewHolderAdapter that give you a list of homogeneous ViewHolder
 * @param <T> the king of object into this adapter
 */
public class SimpleAdapter<T> extends AbsViewHolderAdapter<T> {

    private Class<? extends AbsViewHolder<? extends T>> mViewHolderClass;

    private int mLayoutResId;

    /**
     * Constructor
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects the objects to put in this adapter
     */
    public SimpleAdapter(int layoutResId,
            Class<? extends AbsViewHolder<? extends T>> viewHolderClass, T... objects) {
        this(layoutResId, viewHolderClass, new ArrayList<T>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects the objects to put in this adapter
     */
    public SimpleAdapter(int layoutResId,
            Class<? extends AbsViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        super(objects);
        mViewHolderClass = viewHolderClass;
        mLayoutResId = layoutResId;
    }

    @Override
    protected Class<? extends AbsViewHolder<? extends T>> getViewHolderClass(int viewType) {
        return mViewHolderClass;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return mLayoutResId;
    }
}
