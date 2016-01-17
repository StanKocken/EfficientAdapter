package com.skocken.efficientadapter.example.models;

public class Music implements Item {

    private String mArtist;

    public Music(String artist) {
        mArtist = artist;
    }

    public String getArtist() {
        return mArtist;
    }

    @Override
    public String toString() {
        return mArtist;
    }
}
