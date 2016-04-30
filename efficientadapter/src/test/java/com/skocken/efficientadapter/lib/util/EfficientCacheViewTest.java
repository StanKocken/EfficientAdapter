package com.skocken.efficientadapter.lib.util;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.view.View;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EfficientCacheViewTest extends TestCase {

    private static final int ID_ROOT_VIEW = 1;

    private static final int ID_PARENT_1 = 10;

    private static final int ID_CHILD_1 = 101;

    private static final int ID_CHILD_2 = 102;

    private static final int ID_PARENT_2 = 20;

    @Mock
    private View mRootView;

    @Mock
    private View mViewParent2;

    @Mock
    private View mViewParent2Child1;

    @Mock
    private View mViewParent2Child2;

    @Mock
    private View mViewParent1;

    @Mock
    private View mViewParent1Child1;

    @Mock
    private View mViewParent1Child2;

    private EfficientCacheView mSubject;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        when(mRootView.findViewById(eq(ID_ROOT_VIEW))).thenReturn(mRootView);
        when(mRootView.findViewById(eq(ID_PARENT_1))).thenReturn(mViewParent1);
        when(mRootView.findViewById(eq(ID_PARENT_2))).thenReturn(mViewParent2);
        when(mRootView.findViewById(eq(ID_CHILD_1))).thenReturn(mViewParent1Child1);
        when(mRootView.findViewById(eq(ID_CHILD_2))).thenReturn(mViewParent1Child2);

        when(mViewParent1.findViewById(eq(ID_CHILD_1))).thenReturn(mViewParent1Child1);
        when(mViewParent1.findViewById(eq(ID_CHILD_2))).thenReturn(mViewParent1Child2);
        when(mViewParent2.findViewById(eq(ID_CHILD_1))).thenReturn(mViewParent2Child1);
        when(mViewParent2.findViewById(eq(ID_CHILD_2))).thenReturn(mViewParent2Child2);

        mSubject = new EfficientCacheView(mRootView);
    }

    @Test
    public void testGetView() throws Exception {
        assertEquals(mRootView, mSubject.getView());
    }

    @Test
    public void testReturnRootView() throws Exception {
        assertEquals(mRootView, mSubject.findViewByIdEfficient(ID_ROOT_VIEW));
    }

    @Test
    public void testReturnParents() throws Exception {
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        assertEquals(mViewParent2, mSubject.findViewByIdEfficient(ID_PARENT_2));
    }

    @Test
    public void testReturnChildOfParent1ByDefault() throws Exception {
        assertEquals(mViewParent1Child1, mSubject.findViewByIdEfficient(ID_CHILD_1));
        assertEquals(mViewParent1Child2, mSubject.findViewByIdEfficient(ID_CHILD_2));
    }

    @Test
    public void testReturnChildOfParents() throws Exception {
        assertEquals(mViewParent1Child1, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_1));
        assertEquals(mViewParent1Child2, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_2));
        assertEquals(mViewParent2Child1, mSubject.findViewByIdEfficient(ID_PARENT_2, ID_CHILD_1));
        assertEquals(mViewParent2Child2, mSubject.findViewByIdEfficient(ID_PARENT_2, ID_CHILD_2));
    }

    @Test
    public void testUseCacheTheSecondTime() throws Exception {
        verify(mRootView, times(0)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        verify(mRootView, times(1)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        verify(mRootView, times(1)).findViewById(anyInt());
    }

    @Test
    public void testClearCache() throws Exception {
        verify(mRootView, times(0)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        verify(mRootView, times(1)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        verify(mRootView, times(1)).findViewById(anyInt());
        mSubject.clearViewsCached();
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        verify(mRootView, times(2)).findViewById(anyInt());
    }

    @Test
    public void testClearCacheViewSpecific() throws Exception {
        verify(mRootView, times(0)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        assertEquals(mViewParent2, mSubject.findViewByIdEfficient(ID_PARENT_2));
        verify(mRootView, times(2)).findViewById(anyInt());
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        assertEquals(mViewParent2, mSubject.findViewByIdEfficient(ID_PARENT_2));
        verify(mRootView, times(2)).findViewById(anyInt());
        mSubject.clearViewCached(ID_PARENT_1);
        assertEquals(mViewParent1, mSubject.findViewByIdEfficient(ID_PARENT_1));
        assertEquals(mViewParent2, mSubject.findViewByIdEfficient(ID_PARENT_2));
        verify(mRootView, times(3)).findViewById(anyInt());
    }

    @Test
    public void testClearCacheViewSpecificParent() throws Exception {
        verify(mRootView, times(0)).findViewById(anyInt());
        assertEquals(mViewParent1Child1, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_1));
        assertEquals(mViewParent1Child2, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_2));
        assertEquals(mViewParent2Child1, mSubject.findViewByIdEfficient(ID_PARENT_2, ID_CHILD_1));
        verify(mRootView, times(2)).findViewById(anyInt());
        verify(mViewParent1, times(2)).findViewById(anyInt());
        assertEquals(mViewParent1Child1, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_1));
        assertEquals(mViewParent1Child2, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_2));
        assertEquals(mViewParent2Child1, mSubject.findViewByIdEfficient(ID_PARENT_2, ID_CHILD_1));
        verify(mRootView, times(2)).findViewById(anyInt());
        verify(mViewParent1, times(2)).findViewById(anyInt());
        mSubject.clearViewCached(ID_PARENT_1, ID_CHILD_1);
        assertEquals(mViewParent1Child1, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_1));
        assertEquals(mViewParent1Child2, mSubject.findViewByIdEfficient(ID_PARENT_1, ID_CHILD_2));
        assertEquals(mViewParent2Child1, mSubject.findViewByIdEfficient(ID_PARENT_2, ID_CHILD_1));
        verify(mRootView, times(2)).findViewById(anyInt());
        verify(mViewParent1, times(3)).findViewById(anyInt());
    }
}