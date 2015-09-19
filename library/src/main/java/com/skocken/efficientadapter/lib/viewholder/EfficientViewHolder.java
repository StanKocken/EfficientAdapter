package com.skocken.efficientadapter.lib.viewholder;

import com.skocken.efficientadapter.lib.util.EfficientCacheView;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class EfficientViewHolder<T> extends RecyclerView.ViewHolder {

    private final EfficientCacheView mCacheView;

    private int mLastBindPosition = -1;

    private T mObject;

    /**
     * @param itemView the root view of the view holder. This parameter cannot be null.
     * @throws NullPointerException if the view is null
     */
    public EfficientViewHolder(View itemView) {
        super(itemView);
        mCacheView = createCacheView(itemView);
    }

    EfficientCacheView createCacheView(View itemView) {
        return new EfficientCacheView(itemView);
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param object   the object subject of this update
     */
    public void onBindView(Object object, int position) {
        mObject = (T) object;
        mLastBindPosition = position;
        updateView(mCacheView.getView().getContext(), mObject);
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param context context of the root view
     * @param object  the object subject of this update
     */
    protected abstract void updateView(Context context, T object);

    /**
     * Get the last object set to this viewholder
     */
    public T getObject() {
        return mObject;
    }

    /**
     * @return the getAdapterPosition() if available, or the last bind position otherwise
     */
    public int getLastBindPosition() {
        int adapterPosition = getAdapterPosition();
        if(adapterPosition == -1) {
            return mLastBindPosition;
        } else {
            return adapterPosition;
        }
    }

    /**
     * Get the root view for the ViewHolder (the one passed into the constructor)
     *
     * @return The ViewHolder's root view, or null if it has no layout.
     */
    public View getView() {
        return mCacheView.getView();
    }

    /**
     * Returns the context the view is running in, through which it can
     * access the current theme, resources, etc.
     *
     * @return The view's Context.
     */
    public Context getContext() {
        return mCacheView.getView().getContext();
    }

    /**
     * Returns the resources associated with this view.
     *
     * @return Resources object.
     */
    public Resources getResources() {
        return mCacheView.getView().getResources();
    }

    /**
     * Called when a view created by the adapter has been recycled.
     */
    public void onViewRecycled() {
    }

    /**
     * Called when a view created by the adapter has been attached to a window.
     */
    public void onViewAttachedToWindow() {
    }

    /**
     * Called when a view created by the adapter has been detached from its window.
     */
    public void onViewDetachedFromWindow() {
    }

    /**
     * Determine if a click listener should be automatically added to the view of this view holder
     *
     * @return true you want to have this view clickable
     */
    public boolean isClickable() {
        return true;
    }

    /**
     * Determine if a long click listener should be automatically added to the view of this view
     * holder
     *
     * @return true you want to have this view clickable
     */
    public boolean isLongClickable() {
        return true;
    }

    /**
     * Helper for {@link EfficientCacheView#clearViewsCached()}
     */
    public void clearViewsCached() {
        mCacheView.clearViewsCached();
    }

    /**
     * Helper for {@link EfficientCacheView#clearViewCached(int)}
     */
    public void clearViewCached(int viewId) {
        mCacheView.clearViewCached(viewId);
    }

    /**
     * Helper for {@link EfficientCacheView#clearViewCached(int, int)}
     */
    public void clearViewCached(int parentId, int viewId) {
        mCacheView.clearViewCached(parentId, viewId);
    }

    /**
     * Helper for {@link EfficientCacheView#findViewByIdEfficient(int)}
     */
    public <T extends View> T findViewByIdEfficient(int id) {
        return mCacheView.findViewByIdEfficient(id);
    }

    /**
     * Helper for {@link EfficientCacheView#findViewByIdEfficient(int, int)}
     */
    public <T extends View> T findViewByIdEfficient(int parentId, int id) {
        return mCacheView.findViewByIdEfficient(parentId, id);
    }
}
