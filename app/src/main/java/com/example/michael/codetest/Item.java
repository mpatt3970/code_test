package com.example.michael.codetest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michael on 1/6/15.
 */
public class Item {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("imageURL")
    private String mURL;

    @SerializedName("author")
    private String mAuthor;

    public String getTitle() {
        return mTitle;
    }

    public String getImage() {
        return mURL;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String toString() {
        return "TITLE: " + mTitle + ", URL: " + mURL + ", Author: " + mAuthor;
    }
}
