package com.skocken.efficientadapter.lib.viewholder;

import com.skocken.efficientadapter.lib.util.EfficientCacheView;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EfficientViewHolderTest extends TestCase {

    @Test
    public void testForwardGetView() throws Exception {
        View view = Mockito.mock(View.class);
        EfficientViewHolder subject = new TestEfficientViewHolder(view);
        subject.getContext();
        assertEquals(view, subject.getView());
    }

    @Test
    public void testForwardGetContext() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();
        subject.getContext();
        View view = subject.getView();
        verify(view, times(1)).getContext();
    }

    @Test
    public void testForwardGetResources() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();
        subject.getResources();
        View view = subject.getView();
        verify(view, times(1)).getResources();
    }

    @Test
    public void testOnBindView() throws Exception {

        int expectedPosition = 23;
        Object expectedObject = new Object();

        TestEfficientViewHolder subject = newEfficientViewHolder();

        assertEquals(-1, subject.getLastBindPosition());
        assertNull(subject.getObject());
        assertEquals(0, subject.getNbCallUpdateView());

        subject.onBindView(expectedObject, expectedPosition);

        assertEquals(expectedPosition, subject.getLastBindPosition());
        assertEquals(expectedObject, subject.getObject());
        assertEquals(1, subject.getNbCallUpdateView());
    }

    @Test
    public void testForwardToEfficientCacheView() throws Exception {

        int expectedViewId = 234;
        int expectedParentViewId = 879;

        final EfficientCacheView cacheView = Mockito.mock(EfficientCacheView.class);

        View view = Mockito.mock(View.class);
        EfficientViewHolder subject = new EfficientViewHolder(view) {
            @Override
            EfficientCacheView createCacheView(View itemView) {
                return cacheView;
            }

            @Override
            protected void updateView(@NonNull Context context, Object item) {
            }
        };

        subject.clearViewsCached();
        verify(cacheView, times(1)).clearViewsCached();

        subject.clearViewCached(expectedViewId);
        verify(cacheView, times(1)).clearViewCached(expectedViewId);

        subject.clearViewCached(expectedParentViewId, expectedViewId);
        verify(cacheView, times(1)).clearViewCached(expectedParentViewId, expectedViewId);

        subject.findViewByIdEfficient(expectedViewId);
        verify(cacheView, times(1)).findViewByIdEfficient(expectedViewId);

        subject.findViewByIdEfficient(expectedParentViewId, expectedViewId);
        verify(cacheView, times(1)).findViewByIdEfficient(expectedParentViewId, expectedViewId);

    }

    @Test
    public void testDefaultClickListenerResult() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();

        assertTrue(subject.isClickable());
        assertTrue(subject.isLongClickable());
    }

    @Test
    public void testCallableOnViewRecycled() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();
        subject.onViewRecycled();
    }

    @Test
    public void testCallableOnViewAttachedToWindow() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();
        subject.onViewAttachedToWindow();
    }

    @Test
    public void testCallableOnViewDetachedFromWindow() throws Exception {
        EfficientViewHolder subject = newEfficientViewHolder();
        subject.onViewDetachedFromWindow();
    }

    @NonNull
    private TestEfficientViewHolder newEfficientViewHolder() {
        View view = Mockito.mock(View.class);
        return new TestEfficientViewHolder(view);
    }

    @Test
    public void testDelegateToViewHolder() throws Exception {
        final EfficientCacheView cacheView = Mockito.mock(EfficientCacheView.class);
        View view = Mockito.mock(View.class);
        EfficientViewHolder subject = new EfficientViewHolder(view) {
            @Override
            EfficientCacheView createCacheView(View itemView) {
                return cacheView;
            }

            @Override
            protected void updateView(@NonNull Context context, Object item) {
            }
        };
        int expectedViewId = 234;

        subject.setBackground(expectedViewId, Mockito.mock(Drawable.class));
        subject.setBackgroundColor(expectedViewId, 2);
        subject.setBackgroundResource(expectedViewId, android.R.drawable.btn_default);
        subject.setTag(expectedViewId, new Object());
        subject.setTag(expectedViewId, 23, new Object());
        subject.setText(expectedViewId, "fake_text");
        subject.setText(expectedViewId, android.R.string.cancel);
        subject.setTextColor(expectedViewId, 2);
        subject.setTextSize(expectedViewId, 2);
        subject.setTextSize(expectedViewId, 3, 2);
        subject.setImageViewResource(expectedViewId, android.R.drawable.btn_default);
        subject.setImageDrawable(expectedViewId, Mockito.mock(Drawable.class));
        subject.setImageUri(expectedViewId, Mockito.mock(Uri.class));
        subject.setImageBitmap(expectedViewId, Mockito.mock(Bitmap.class));

        verify(cacheView, times(14)).findViewByIdEfficient(expectedViewId);
    }

    private static class TestEfficientViewHolder extends EfficientViewHolder {

        private int mNbCallUpdateView = 0;

        public TestEfficientViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void updateView(@NonNull Context context, Object item) {
            mNbCallUpdateView++;
        }

        public int getNbCallUpdateView() {
            return mNbCallUpdateView;
        }
    }
}