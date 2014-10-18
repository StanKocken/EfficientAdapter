package com.skocken.efficientadapter.example.models;

public class Book {

    private String mAuthor;

    private String mTitle;

    private String mSummary;

    public Book(String author, String title, String summary) {
        mAuthor = author;
        mTitle = title;
        mSummary = summary;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
