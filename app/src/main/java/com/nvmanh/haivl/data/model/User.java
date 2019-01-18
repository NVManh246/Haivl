package com.nvmanh.haivl.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("username")
    private String mUsername;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("avatar")
    private String mAvatar;

    public User(String username, String password, String email, String displayName, String avatar) {
        mUsername = username;
        mPassword = password;
        mEmail = email;
        mDisplayName = displayName;
        mAvatar = avatar;
    }

    protected User(Parcel in) {
        mUsername = in.readString();
        mPassword = in.readString();
        mEmail = in.readString();
        mDisplayName = in.readString();
        mAvatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getAvatar() {
        return mAvatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUsername);
        parcel.writeString(mPassword);
        parcel.writeString(mEmail);
        parcel.writeString(mDisplayName);
        parcel.writeString(mAvatar);
    }
}
