package com.skocken.efficientadapter.lib.util;

import android.util.SparseArray;
import android.view.View;

public class EfficientCacheView {

    private SparseArray<SparseArray<View>> mSparseSparseArrayView = new SparseArray<>();

    private final View mView;

    public EfficientCacheView(View view) {
        mView = view;
    }

    public View getView() {
        return mView;
    }

    /**
     * Clear the cache of views retrieved
     */
    public void clearViewsCached() {
        mSparseSparseArrayView.clear();
    }

    /**
     * Clear the cache for the view specify
     *
     * @param viewId id of the view to remove from the cache
     */
    public void clearViewCached(int viewId) {
        clearViewCached(0, viewId);
    }

    /**
     * Clear the cache for the view specify
     *
     * @param parentId the parent id of the view to remove (if the view was retrieve with this
     *                 parent id)
     * @param viewId   id of the view to remove from the cache
     */
    public void clearViewCached(int parentId, int viewId) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent != null) {
            sparseArrayViewsParent.remove(viewId);
        }
    }

    /**
     * Look for a child view with the given id.  If this view has the given
     * id, return this view.
     * <br />
     * The method is more efficient than a "normal" "findViewById": the second time you will
     * called it with the same argument, the view return will come from the cache.
     *
     * @param id The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    public <T extends View> T findViewByIdEfficient(int id) {
        return castView(findViewByIdEfficient(0, id));
    }

    /**
     * Look for a child view of the parent view id with the given id.  If this view has the given
     * id, return this view.
     * <br />
     * The method is more efficient than a "normal" "findViewById" : the second time you will
     * called this method with the same argument, the view return will come from the cache.
     *
     * @param id The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    public <T extends View> T findViewByIdEfficient(int parentId, int id) {
        View viewRetrieve = retrieveFromCache(parentId, id);
        if (viewRetrieve == null) {
            viewRetrieve = findViewById(parentId, id);
            if (viewRetrieve != null) {
                storeView(parentId, id, viewRetrieve);
            }
        }
        return castView(viewRetrieve);
    }

    private <T extends View> T castView(View view) {
        //noinspection unchecked
        return (T) view;
    }

    private void storeView(int parentId, int id, View viewRetrieve) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent == null) {
            sparseArrayViewsParent = new SparseArray<>();
            mSparseSparseArrayView.put(parentId, sparseArrayViewsParent);
        }
        sparseArrayViewsParent.put(id, viewRetrieve);
    }

    private View findViewById(int parentId, int id) {
        if (parentId == 0) {
            return mView.findViewById(id);
        } else {
            View parent = findViewByIdEfficient(parentId);
            if (parent != null) {
                return parent.findViewById(id);
            } else {
                return null;
            }
        }
    }

    private View retrieveFromCache(int parentId, int id) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent != null) {
            View viewRetrieve = sparseArrayViewsParent.get(id);
            if (viewRetrieve == null) {
                // dead reference
                sparseArrayViewsParent.remove(id);
            } else {
                return viewRetrieve;
            }
        }
        if (parentId == 0) {
            return retrieveFromCache(id);
        } else {
            return null;
        }
    }

    private View retrieveFromCache(int id) {
        for (int i = 0; i < mSparseSparseArrayView.size(); i++) {
            int parentId = mSparseSparseArrayView.keyAt(i);
            if (parentId != 0) {
                View viewRetrieve = retrieveFromCache(parentId, id);
                if (viewRetrieve != null) {
                    return viewRetrieve;
                }
            }
        }
        return null;
    }

}
