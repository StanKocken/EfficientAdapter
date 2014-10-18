package com.skocken.efficientadapter.lib.adapter;

import java.util.List;

/**
 * Common adapter to use an heterogeneous list of ViewHolder into a RecyclerView
 */
public abstract class HeterogeneousAdapter extends AbsViewHolderAdapter<Object> {

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    public HeterogeneousAdapter(Object... objects) {
        super(objects);
    }

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    public HeterogeneousAdapter(List<Object> objects) {
        super(objects);
    }

    @Override
    public abstract int getItemViewType(int position);

}
