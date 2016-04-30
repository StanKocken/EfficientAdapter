package com.skocken.efficientadapter.lib.util;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ViewHelperTest extends TestCase {

    private static final int TEXTVIEW_ID = 1234;

    private static final int IMAGEVIEW_ID = 2345;

    private static final int VIEW_ID = 3456;

    private static final int NULL_VIEW_ID = 4567;

    @Mock
    private EfficientCacheView mCacheView;

    @Mock
    private TextView mTextView;

    @Mock
    private ImageView mImageView;

    @Mock
    private View mView;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        when(mCacheView.findViewByIdEfficient(eq(TEXTVIEW_ID))).thenReturn(mTextView);
        when(mCacheView.findViewByIdEfficient(eq(IMAGEVIEW_ID))).thenReturn(mImageView);
        when(mCacheView.findViewByIdEfficient(eq(VIEW_ID))).thenReturn(mView);
        when(mCacheView.findViewByIdEfficient(eq(NULL_VIEW_ID))).thenReturn(null);
    }

    @Test
    public void testSetVisibility_anyView() throws Exception {
        ViewHelper.setVisibility(mCacheView, NULL_VIEW_ID, View.VISIBLE);
        ViewHelper.setVisibility(mCacheView, VIEW_ID, View.VISIBLE);
        ViewHelper.setVisibility(mCacheView, IMAGEVIEW_ID, View.INVISIBLE);
        ViewHelper.setVisibility(mCacheView, TEXTVIEW_ID, View.GONE);

        verify(mView).setVisibility(View.VISIBLE);
        verify(mImageView).setVisibility(View.INVISIBLE);
        verify(mTextView).setVisibility(View.GONE);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetBackground() throws Exception {
        Drawable drawable = Mockito.mock(Drawable.class);
        ViewHelper.setBackground(mCacheView, NULL_VIEW_ID, drawable);
        ViewHelper.setBackground(mCacheView, VIEW_ID, drawable);
        ViewHelper.setBackground(mCacheView, IMAGEVIEW_ID, drawable);
        ViewHelper.setBackground(mCacheView, TEXTVIEW_ID, drawable);

        verify(mView).setBackgroundDrawable(drawable);
        verify(mImageView).setBackgroundDrawable(drawable);
        verify(mTextView).setBackgroundDrawable(drawable);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetBackgroundColor() throws Exception {

        int color1 = 1;
        int color2 = 2;
        int color3 = 3;
        int color4 = 4;

        ViewHelper.setBackgroundColor(mCacheView, NULL_VIEW_ID, color1);
        ViewHelper.setBackgroundColor(mCacheView, VIEW_ID, color2);
        ViewHelper.setBackgroundColor(mCacheView, IMAGEVIEW_ID, color3);
        ViewHelper.setBackgroundColor(mCacheView, TEXTVIEW_ID, color4);

        verify(mView).setBackgroundColor(color2);
        verify(mImageView).setBackgroundColor(color3);
        verify(mTextView).setBackgroundColor(color4);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetBackgroundResource() throws Exception {

        int drawableRes1 = android.R.drawable.alert_dark_frame;
        int drawableRes2 = drawableRes1 + 1;
        int drawableRes3 = drawableRes1 + 2;
        int drawableRes4 = drawableRes1 + 3;

        ViewHelper.setBackgroundResource(mCacheView, NULL_VIEW_ID, drawableRes1);
        ViewHelper.setBackgroundResource(mCacheView, VIEW_ID, drawableRes2);
        ViewHelper.setBackgroundResource(mCacheView, IMAGEVIEW_ID, drawableRes3);
        ViewHelper.setBackgroundResource(mCacheView, TEXTVIEW_ID, drawableRes4);

        verify(mView).setBackgroundResource(drawableRes2);
        verify(mImageView).setBackgroundResource(drawableRes3);
        verify(mTextView).setBackgroundResource(drawableRes4);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetTag() throws Exception {

        Object tag1 = new Object();
        Object tag2 = new Object();
        Object tag3 = new Object();
        Object tag4 = new Object();

        ViewHelper.setTag(mCacheView, NULL_VIEW_ID, tag1);
        ViewHelper.setTag(mCacheView, VIEW_ID, tag2);
        ViewHelper.setTag(mCacheView, IMAGEVIEW_ID, tag3);
        ViewHelper.setTag(mCacheView, TEXTVIEW_ID, tag4);

        verify(mView).setTag(tag2);
        verify(mImageView).setTag(tag3);
        verify(mTextView).setTag(tag4);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetTagWithTag() throws Exception {

        int tagId1 = 1;
        int tagId2 = 2;
        int tagId3 = 3;
        int tagId4 = 4;

        Object tag1 = new Object();
        Object tag2 = new Object();
        Object tag3 = new Object();
        Object tag4 = new Object();

        ViewHelper.setTag(mCacheView, NULL_VIEW_ID, tagId1, tag1);
        ViewHelper.setTag(mCacheView, VIEW_ID, tagId2, tag2);
        ViewHelper.setTag(mCacheView, IMAGEVIEW_ID, tagId3, tag3);
        ViewHelper.setTag(mCacheView, TEXTVIEW_ID, tagId4, tag4);

        verify(mView).setTag(tagId2, tag2);
        verify(mImageView).setTag(tagId3, tag3);
        verify(mTextView).setTag(tagId4, tag4);
        verifyNoMoreInteractions(mView, mImageView, mTextView);
    }

    @Test
    public void testSetText_textView() throws Exception {
        CharSequence text = "fake_text";
        ViewHelper.setText(mCacheView, TEXTVIEW_ID, text);
        verify(mTextView).setText(text);
        verifyNoMoreInteractions(mTextView);
    }

    @Test
    public void testSetText_notTextView() throws Exception {
        CharSequence text = "fake_text";
        ViewHelper.setText(mCacheView, NULL_VIEW_ID, text);
        ViewHelper.setText(mCacheView, VIEW_ID, text);
        ViewHelper.setText(mCacheView, IMAGEVIEW_ID, text);
        verifyZeroInteractions(mImageView, mView);
    }

    @Test
    public void testSetTextWithRes_textView() throws Exception {
        int textRes = android.R.string.cancel;
        ViewHelper.setText(mCacheView, TEXTVIEW_ID, textRes);
        verify(mTextView).setText(textRes);
        verifyNoMoreInteractions(mTextView);
    }

    @Test
    public void testSetTextWithRes_notTextView() throws Exception {
        int textRes = android.R.string.cancel;
        ViewHelper.setText(mCacheView, NULL_VIEW_ID, textRes);
        ViewHelper.setText(mCacheView, VIEW_ID, textRes);
        ViewHelper.setText(mCacheView, IMAGEVIEW_ID, textRes);
        verifyZeroInteractions(mImageView, mView);
    }

    @Test
    public void testSetTextColorWithRes_textView() throws Exception {
        int color = 3;
        ViewHelper.setTextColor(mCacheView, TEXTVIEW_ID, color);
        verify(mTextView).setTextColor(color);
        verifyNoMoreInteractions(mTextView);
    }

    @Test
    public void testSetTextColorWithRes_notTextView() throws Exception {
        int color = 3;
        ViewHelper.setTextColor(mCacheView, NULL_VIEW_ID, color);
        ViewHelper.setTextColor(mCacheView, VIEW_ID, color);
        ViewHelper.setTextColor(mCacheView, IMAGEVIEW_ID, color);
        verifyZeroInteractions(mImageView, mView);
    }

    @Test
    public void testSetTextSizeWithRes_textView() throws Exception {
        int size = 3;
        ViewHelper.setTextSize(mCacheView, TEXTVIEW_ID, size);
        verify(mTextView).setTextSize(size);
        verifyNoMoreInteractions(mTextView);
    }

    @Test
    public void testSetTextSizeWithRes_notTextView() throws Exception {
        int size = 3;
        ViewHelper.setTextSize(mCacheView, NULL_VIEW_ID, size);
        ViewHelper.setTextSize(mCacheView, VIEW_ID, size);
        ViewHelper.setTextSize(mCacheView, IMAGEVIEW_ID, size);
        verifyZeroInteractions(mImageView, mView);
    }

    @Test
    public void testSetTextSizeWithUnitWithRes_textView() throws Exception {
        int size = 3;
        int unit = 2;
        ViewHelper.setTextSize(mCacheView, TEXTVIEW_ID, unit, size);
        verify(mTextView).setTextSize(unit, size);
        verifyNoMoreInteractions(mTextView);
    }

    @Test
    public void testSetTextSizeWithUnitWithRes_notTextView() throws Exception {
        int size = 3;
        int unit = 2;
        ViewHelper.setTextSize(mCacheView, NULL_VIEW_ID, unit, size);
        ViewHelper.setTextSize(mCacheView, VIEW_ID, unit, size);
        ViewHelper.setTextSize(mCacheView, IMAGEVIEW_ID, unit, size);
        verifyZeroInteractions(mImageView, mView);
    }

    @Test
    public void testSetImageResource_imageView() throws Exception {
        int res = android.R.drawable.btn_dialog;
        ViewHelper.setImageResource(mCacheView, IMAGEVIEW_ID, res);
        verify(mImageView).setImageResource(res);
        verifyNoMoreInteractions(mImageView);
    }

    @Test
    public void testSetImageResource_notImageView() throws Exception {
        int res = android.R.drawable.btn_dialog;
        ViewHelper.setImageResource(mCacheView, NULL_VIEW_ID, res);
        ViewHelper.setImageResource(mCacheView, VIEW_ID, res);
        ViewHelper.setImageResource(mCacheView, TEXTVIEW_ID, res);
        verifyZeroInteractions(mTextView, mView);
    }

    @Test
    public void testSetImageDrawable_imageView() throws Exception {
        Drawable drawable = Mockito.mock(Drawable.class);
        ViewHelper.setImageDrawable(mCacheView, IMAGEVIEW_ID, drawable);
        verify(mImageView).setImageDrawable(drawable);
        verifyNoMoreInteractions(mImageView);
    }

    @Test
    public void testSetImageDrawable_notImageView() throws Exception {
        Drawable drawable = Mockito.mock(Drawable.class);
        ViewHelper.setImageDrawable(mCacheView, NULL_VIEW_ID, drawable);
        ViewHelper.setImageDrawable(mCacheView, VIEW_ID, drawable);
        ViewHelper.setImageDrawable(mCacheView, TEXTVIEW_ID, drawable);
        verifyZeroInteractions(mTextView, mView);
    }

    @Test
    public void testSetImageURI_imageView() throws Exception {
        Uri uri = Mockito.mock(Uri.class);
        ViewHelper.setImageUri(mCacheView, IMAGEVIEW_ID, uri);
        verify(mImageView).setImageURI(uri);
        verifyNoMoreInteractions(mImageView);
    }

    @Test
    public void testSetImageURI_notImageView() throws Exception {
        Uri uri = Mockito.mock(Uri.class);
        ViewHelper.setImageUri(mCacheView, NULL_VIEW_ID, uri);
        ViewHelper.setImageUri(mCacheView, VIEW_ID, uri);
        ViewHelper.setImageUri(mCacheView, TEXTVIEW_ID, uri);
        verifyZeroInteractions(mTextView, mView);
    }

    @Test
    public void testSetImageBitmap_imageView() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);
        ViewHelper.setImageBitmap(mCacheView, IMAGEVIEW_ID, bitmap);
        verify(mImageView).setImageBitmap(bitmap);
        verifyNoMoreInteractions(mImageView);
    }

    @Test
    public void testSetImageBitmap_notImageView() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);
        ViewHelper.setImageBitmap(mCacheView, NULL_VIEW_ID, bitmap);
        ViewHelper.setImageBitmap(mCacheView, VIEW_ID, bitmap);
        ViewHelper.setImageBitmap(mCacheView, TEXTVIEW_ID, bitmap);
        verifyZeroInteractions(mTextView, mView);
    }

}