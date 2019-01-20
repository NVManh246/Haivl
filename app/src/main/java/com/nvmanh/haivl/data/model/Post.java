package com.nvmanh.haivl.data.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mContent;
    @SerializedName("image")
    private String mImagePath;
    @SerializedName("time")
    private String mTime;
    @SerializedName("user")
    private User mOwner;
    @SerializedName("isLike")
    private boolean mIsLike;
    @SerializedName("numberLike")
    private int mNumberLike;
    @SerializedName("numberComment")
    private int mNumberComment;

    public Post(int id, String content, String imagePath, String time, User owner,
                boolean isLike, int numberLike, int numberComment) {
        mId = id;
        mContent = content;
        mImagePath = imagePath;
        mTime = time;
        mOwner = owner;
        mIsLike = isLike;
        mNumberLike = numberLike;
        mNumberComment = numberComment;
    }

    public int getId() {
        return mId;
    }

    public String getContent() {
        return mContent;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public String getTime() {
        return mTime;
    }

    public User getOwner() {
        return mOwner;
    }

    public boolean isLike() {
        return mIsLike;
    }

    public int getNumberLike() {
        return mNumberLike;
    }

    public int getNumberComment() {
        return mNumberComment;
    }
}
