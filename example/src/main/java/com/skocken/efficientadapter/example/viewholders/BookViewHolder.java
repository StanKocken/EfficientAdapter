package com.skocken.efficientadapter.example.viewholders;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Book;
import com.skocken.efficientadapter.lib.viewholder.AbsViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class BookViewHolder extends AbsViewHolder<Book> {

    public BookViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void updateView(Context context, Book object) {
        ((TextView) findViewByIdEfficient(R.id.author_textview)).setText(
                object.getAuthor());
        ((TextView) findViewByIdEfficient(R.id.title_textview)).setText(
                object.getTitle());
        ((TextView) findViewByIdEfficient(R.id.summary_textview)).setText(
                object.getSummary());
    }
}
