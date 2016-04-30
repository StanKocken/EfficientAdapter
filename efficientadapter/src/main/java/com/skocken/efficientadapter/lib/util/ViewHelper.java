package com.skocken.efficientadapter.lib.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHelper {

    /**
     * Equivalent to calling View.setVisibility
     *
     * @param cacheView  The cache of views to get the view from
     * @param viewId     The id of the view whose visibility should change
     * @param visibility The new visibility for the view
     */
    public static void setVisibility(EfficientCacheView cacheView, int viewId, int visibility) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * Equivalent to calling View.setBackground
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose background should change
     * @param drawable  The new background for the view
     */
    public static void setBackground(EfficientCacheView cacheView, int viewId, Drawable drawable) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackgroundDrawable(drawable);
            } else {
                view.setBackground(drawable);
            }
        }
    }

    /**
     * Equivalent to calling View.setBackgroundColor
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose background should change
     * @param color     The new background color for the view
     */
    public static void setBackgroundColor(EfficientCacheView cacheView, int viewId,
            @ColorInt int color) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    /**
     * Equivalent to calling View.setBackgroundResource
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose background should change
     * @param resid     The new background resource for the view
     */
    public static void setBackgroundResource(EfficientCacheView cacheView, int viewId,
            @DrawableRes int resid) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            view.setBackgroundResource(resid);
        }
    }

    /**
     * Equivalent to calling View.setTag
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose tag should change
     * @param tag       An Object to tag the view with
     */
    public static void setTag(EfficientCacheView cacheView, int viewId, Object tag) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            view.setTag(tag);
        }
    }

    /**
     * Equivalent to calling View.setTag
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose tag should change
     * @param key       The key identifying the tag
     * @param tag       An Object to tag the view with
     */
    public static void setTag(EfficientCacheView cacheView, int viewId, int key, Object tag) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view != null) {
            view.setTag(key, tag);
        }
    }

    /**
     * Equivalent to calling TextView.setText
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose text should change
     * @param text      The new text for the view
     */
    public static void setText(EfficientCacheView cacheView, int viewId, CharSequence text) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    /**
     * Equivalent to calling TextView.setText
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose text should change
     * @param resid     The new text for the view
     */
    public static void setText(EfficientCacheView cacheView, int viewId, @StringRes int resid) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(resid);
        }
    }

    /**
     * Equivalent to calling TextView.setTextColor
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose text color should change
     * @param color     The new color for the view
     */
    public static void setTextColor(EfficientCacheView cacheView, int viewId, @ColorInt int color) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }

    /**
     * Equivalent to calling TextView.setTextSize
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose text size should change
     * @param size      The scaled pixel size.
     */
    public static void setTextSize(EfficientCacheView cacheView, int viewId, float size) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(size);
        }
    }

    /**
     * Equivalent to calling TextView.setTextSize
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose text size should change
     * @param unit      The desired dimension unit.
     * @param size      The desired size in the given units.
     */
    public static void setTextSize(EfficientCacheView cacheView, int viewId, int unit, float size) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(unit, size);
        }
    }

    /**
     * Equivalent to calling ImageView.setImageResource
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose image should change
     * @param resId     the resource identifier of the drawable
     */
    public static void setImageResource(EfficientCacheView cacheView, int viewId,
            @DrawableRes int resId) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        }
    }

    /**
     * Equivalent to calling ImageView.setImageDrawable
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose image should change
     * @param drawable  the Drawable to set, or {@code null} to clear the
     *                  content
     */
    public static void setImageDrawable(EfficientCacheView cacheView, int viewId,
            Drawable drawable) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }

    /**
     * Equivalent to calling ImageView.setImageUri
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose image should change
     * @param uri       the Uri of an image, or {@code null} to clear the content
     */
    public static void setImageUri(EfficientCacheView cacheView, int viewId, @Nullable Uri uri) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageURI(uri);
        }
    }

    /**
     * Equivalent to calling ImageView.setImageBitmap
     *
     * @param cacheView The cache of views to get the view from
     * @param viewId    The id of the view whose image should change
     * @param bm        The bitmap to set
     */
    public static void setImageBitmap(EfficientCacheView cacheView, int viewId, Bitmap bm) {
        View view = cacheView.findViewByIdEfficient(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bm);
        }
    }

}
