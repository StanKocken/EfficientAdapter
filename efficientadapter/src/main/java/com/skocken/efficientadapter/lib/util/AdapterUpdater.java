package com.skocken.efficientadapter.lib.util;

import java.util.List;

/**
 * Updater helper for the Adapter.
 * Instead of remove every items and re-set the new list, this helper will only add missing, remove useless, re-order
 * and notify items.
 */
public class AdapterUpdater<T> {

    private final Updater<T> mUpdater;

    public AdapterUpdater(Updater<T> updater) {
        mUpdater = updater;
    }

    public void update(List<T> items) {
        notifyUntouchedItems(items);
        removeUseless(items);
        addMissingAndReorder(items);
    }

    private void notifyUntouchedItems(List<T> items) {
        for (int i = 0; i < mUpdater.size(); i++) {
            T objectInList = mUpdater.get(i);
            if (items.indexOf(objectInList) != -1) {
                mUpdater.notifyItemChanged(i);
            }
        }
    }

    private void removeUseless(List<T> newItems) {
        for (int i = 0; i < mUpdater.size(); i++) {
            T objectInList = mUpdater.get(i);
            if (newItems.indexOf(objectInList) == -1) {
                // remove
                mUpdater.removeAt(i);
                i--;
            }
        }
    }

    private void addMissingAndReorder(List<T> newItems) {
        for (int indexInNew = 0; indexInNew < newItems.size(); indexInNew++) {
            T object = newItems.get(indexInNew);
            int indexInPrevious = getIndexInPrevious(indexInNew, object);

            if (indexInPrevious == -1) {
                // not present, or duplicate, add it
                mUpdater.add(indexInNew, object);
            } else if (indexInPrevious != indexInNew) {
                mUpdater.removeAt(indexInPrevious);
                mUpdater.add(Math.min(mUpdater.size(), indexInNew), object);
            }

        }
    }

    private int getIndexInPrevious(int indexInNew, T object) {
        int indexInPrevious = mUpdater.indexOf(object);

        if (indexInPrevious > -1 && indexInPrevious < indexInNew) {
            // maybe a duplicate
            List<T> currentObjects = mUpdater.getObjects();
            int lastIndexOf = currentObjects.lastIndexOf(object);
            if (lastIndexOf < indexInNew) {
                // need to insert a new one
                indexInPrevious = -1;
            } else if (lastIndexOf > indexInNew) {
                // another is after, let's take it for now
                indexInPrevious = lastIndexOf;
            } else {
                // same object, same position
                indexInPrevious = indexInNew;
            }
        }
        return indexInPrevious;
    }

    public interface Updater<T> {

        int size();

        T get(int index);

        List<T> getObjects();

        void notifyItemChanged(int i);

        void removeAt(int i);

        int indexOf(T item);

        void add(int i, T item);
    }
}
