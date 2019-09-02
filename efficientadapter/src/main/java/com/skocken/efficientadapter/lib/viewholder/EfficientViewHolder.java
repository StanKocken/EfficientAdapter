package com.skocken.efficientadapter.lib.viewholder;

import com.skocken.efficientadapter.lib.adapter.EfficientAdapter;
import com.skocken.efficientadapter.lib.util.EfficientCacheView;
import com.skocken.efficientadapter.lib.util.ViewHelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;

public abstract class EfficientViewHolder<T> extends RecyclerView.ViewHolder {

    private final EfficientCacheView mCacheView;

    private WeakReference<EfficientAdapter<T>> mAdapterRef;

    private ViewHolderClickListener mViewHolderClickListener;
    private ViewHolderLongClickListener mViewHolderLongClickListener;

    private int mLastBindPosition = -1;

    @Nullable
    private T mObject;

    /**
     * @param itemView the root view of the view holder. This parameter cannot be null.
     *
     * @throws NullPointerException if the view is null
     */
    public EfficientViewHolder(View itemView) {
        super(itemView);
        mCacheView = createCacheView(itemView);
    }

    EfficientCacheView createCacheView(View itemView) {
        return new EfficientCacheView(itemView);
    }

    public void setAdapter(EfficientAdapter<T> adapter) {
        if (adapter != getAdapter()) {
            mAdapterRef = new WeakReference<>(adapter);
        }
    }

    @Nullable
    public EfficientAdapter<T> getAdapter() {
        return mAdapterRef == null ? null : mAdapterRef.get();
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param item the object subject of this update
     */
    public void onBindView(@Nullable T item, int position) {
        mObject = item;
        mLastBindPosition = position;
        updateView(mCacheView.getView().getContext(), mObject);
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param context context of the root view
     * @param item  the object subject of this update
     */
    protected abstract void updateView(@NonNull Context context, @Nullable T item);

    /**
     * Get the last object set to this viewholder
     */
    @Nullable
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
     * @return The ViewHolder's root view.
     */
    @NonNull
    public View getView() {
        return mCacheView.getView();
    }

    /**
     * Returns the context the view is running in, through which it can
     * access the current theme, resources, etc.
     *
     * @return The view's Context.
     */
    @NonNull
    public Context getContext() {
        return mCacheView.getView().getContext();
    }

    /**
     * Returns the resources associated with this view.
     *
     * @return Resources object.
     */
    @NonNull
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
     * Get the OnClickListener to call when the user click on the item.
     * @param adapterHasListener true if the calling adapter has a global listener on the item.
     * @return the click listener to be put into the view.
     */
    public View.OnClickListener getOnClickListener(boolean adapterHasListener) {
        if (isClickable() && adapterHasListener) {
            if (mViewHolderClickListener == null) {
                mViewHolderClickListener = new ViewHolderClickListener<>(this);
            }
        } else {
            mViewHolderClickListener = null;
        }
        return mViewHolderClickListener;
    }

    /**
     * Get the OnLongClickListener to call when the user long-click on the item.
     * @param adapterHasListener true if the calling adapter has a global listener on the item.
     * @return the long-click listener to be put into the view.
     */
    public View.OnLongClickListener getOnLongClickListener(boolean adapterHasListener) {
        if (isLongClickable() && adapterHasListener) {
            if (mViewHolderLongClickListener == null) {
                mViewHolderLongClickListener = new ViewHolderLongClickListener<>(this);
            }
        } else {
            mViewHolderLongClickListener = null;
        }
        return mViewHolderLongClickListener;
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
    @Nullable
    public <T extends View> T findViewByIdEfficient(int id) {
        return mCacheView.findViewByIdEfficient(id);
    }

    /**
     * Helper for {@link EfficientCacheView#findViewByIdEfficient(int, int)}
     */
    @Nullable
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

    private static class ViewHolderClickListener<T> implements View.OnClickListener {

        private WeakReference<EfficientViewHolder<T>> mViewHolderRef;

        public ViewHolderClickListener(EfficientViewHolder<T> viewHolder) {
            mViewHolderRef = new WeakReference<>(viewHolder);
        }

        @Override
        public void onClick(View v) {
            EfficientViewHolder<T> viewHolder = mViewHolderRef.get();
            if (viewHolder == null) {
                return;
            }
            EfficientAdapter<T> adapter = viewHolder.getAdapter();
            if (adapter == null) {
                return;
            }
            EfficientAdapter.OnItemClickListener<T> listener = adapter.getOnItemClickListener();
            if (listener == null) {
                return;
            }
            T object = viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            listener.onItemClick(adapter, view, object, position);
        }
    }

    private static class ViewHolderLongClickListener<T> implements View.OnLongClickListener {

        private WeakReference<EfficientViewHolder<T>> mViewHolderRef;

        public ViewHolderLongClickListener(EfficientViewHolder<T> viewHolder) {
            mViewHolderRef = new WeakReference<>(viewHolder);
        }

        @Override
        public boolean onLongClick(View v) {
            EfficientViewHolder<T> viewHolder = mViewHolderRef.get();
            if (viewHolder == null) {
                return false;
            }
            EfficientAdapter<T> adapter = viewHolder.getAdapter();
            if (adapter == null) {
                return false;
            }
            EfficientAdapter.OnItemLongClickListener<T> listener = adapter.getOnItemLongClickListener();
            if (listener == null) {
                return false;
            }
            T object = viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            listener.onLongItemClick(adapter, view, object, position);
            return true;
        }
    }
}
