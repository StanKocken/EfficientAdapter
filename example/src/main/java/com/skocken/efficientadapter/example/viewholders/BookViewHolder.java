package com.skocken.efficientadapter.example.viewholders;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Book;
import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

public class BookViewHolder extends EfficientViewHolder<Book> {

    public BookViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void updateView(@NonNull Context context, Book item) {
        setText(R.id.author_textview, item.getAuthor());
        setText(R.id.title_textview, item.getTitle());

        setText(R.id.summary_textview, item.getSummary());
        // OR
//        TextView textView = findViewByIdEfficient(R.id.summary_textview);
//        textView.setText(object.getSummary());
    }

    @Override
    public boolean isClickable() {
        return false;
    }
}
