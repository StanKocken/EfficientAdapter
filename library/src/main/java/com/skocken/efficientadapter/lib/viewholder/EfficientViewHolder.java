package com.skocken.efficientadapter.lib.viewholder;

import com.skocken.efficientadapter.lib.util.EfficientCacheView;
import com.skocken.efficientadapter.lib.util.ViewHelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
     * @param object the object subject of this update
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
        if (adapterPosition == -1) {
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

    /**
     * Equivalent to calling View.setVisibility
     *
     * @param viewId     The id of the view whose visibility should change
     * @param visibility The new visibility for the view
     */
    public void setVisibility(int viewId, int visibility) {
        ViewHelper.setVisibility(mCacheView, viewId, visibility);
    }

    /**
     * Equivalent to calling View.setBackground
     *
     * @param viewId   The id of the view whose background should change
     * @param drawable The new background for the view
     */
    public void setBackground(int viewId, Drawable drawable) {
        ViewHelper.setBackground(mCacheView, viewId, drawable);
    }

    /**
     * Equivalent to calling View.setBackgroundColor
     *
     * @param viewId The id of the view whose background should change
     * @param color  The new background color for the view
     */
    public void setBackgroundColor(int viewId, @ColorInt int color) {
        ViewHelper.setBackgroundColor(mCacheView, viewId, color);
    }

    /**
     * Equivalent to calling View.setBackgroundResource
     *
     * @param viewId The id of the view whose background should change
     * @param resid  The new background resource for the view
     */
    public void setBackgroundResource(int viewId, @DrawableRes int resid) {
        ViewHelper.setBackgroundResource(mCacheView, viewId, resid);
    }

    /**
     * Equivalent to calling View.setTag
     *
     * @param viewId The id of the view whose tag should change
     * @param tag    An Object to tag the view with
     */
    public void setTag(int viewId, Object tag) {
        ViewHelper.setTag(mCacheView, viewId, tag);
    }

    /**
     * Equivalent to calling View.setTag
     *
     * @param viewId The id of the view whose tag should change
     * @param key    The key identifying the tag
     * @param tag    An Object to tag the view with
     */
    public void setTag(int viewId, int key, Object tag) {
        ViewHelper.setTag(mCacheView, viewId, key, tag);
    }

    /**
     * Equivalent to calling TextView.setText
     *
     * @param viewId The id of the view whose text should change
     * @param text   The new text for the view
     */
    public void setText(int viewId, CharSequence text) {
        ViewHelper.setText(mCacheView, viewId, text);
    }

    /**
     * Equivalent to calling TextView.setText
     *
     * @param viewId The id of the view whose text should change
     * @param resid  The new text for the view
     */
    public void setText(int viewId, @StringRes int resid) {
        ViewHelper.setText(mCacheView, viewId, resid);
    }

    /**
     * Equivalent to calling TextView.setTextColor
     *
     * @param viewId The id of the view whose text color should change
     * @param color  The new color for the view
     */
    public void setTextColor(int viewId, @ColorInt int color) {
        ViewHelper.setTextColor(mCacheView, viewId, color);
    }

    /**
     * Equivalent to calling TextView.setTextSize
     *
     * @param viewId The id of the view whose text size should change
     * @param size   The scaled pixel size.
     */
    public void setTextSize(int viewId, float size) {
        ViewHelper.setTextSize(mCacheView, viewId, size);
    }

    /**
     * Equivalent to calling TextView.setTextSize
     *
     * @param viewId The id of the view whose text size should change
     * @param unit   The desired dimension unit.
     * @param size   The desired size in the given units.
     */
    public void setTextSize(int viewId, int unit, float size) {
        ViewHelper.setTextSize(mCacheView, viewId, unit, size);
    }

    /**
     * Equivalent to calling ImageView.setImageResource
     *
     * @param viewId The id of the view whose image should change
     * @param resId  the resource identifier of the drawable
     */
    public void setImageViewResource(int viewId, @DrawableRes int resId) {
        ViewHelper.setImageResource(mCacheView, viewId, resId);
    }

    /**
     * Equivalent to calling ImageView.setImageDrawable
     *
     * @param viewId   The id of the view whose image should change
     * @param drawable the Drawable to set, or {@code null} to clear the
     *                 content
     */
    public void setImageDrawable(int viewId, Drawable drawable) {
        ViewHelper.setImageDrawable(mCacheView, viewId, drawable);
    }

    /**
     * Equivalent to calling ImageView.setImageUri
     *
     * @param viewId The id of the view whose image should change
     * @param uri    the Uri of an image, or {@code null} to clear the content
     */
    public void setImageUri(int viewId, @Nullable Uri uri) {
        ViewHelper.setImageUri(mCacheView, viewId, uri);
    }

    /**
     * Equivalent to calling ImageView.setImageBitmap
     *
     * @param viewId The id of the view whose image should change
     * @param bm     The bitmap to set
     */
    public void setImageBitmap(int viewId, Bitmap bm) {
        ViewHelper.setImageBitmap(mCacheView, viewId, bm);
    }
}
