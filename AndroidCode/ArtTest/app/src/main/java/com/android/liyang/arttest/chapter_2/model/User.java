package com.android.liyang.arttest.chapter_2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by liyang
 * on 2017/11/21.
 */

public class User implements Parcelable, Serializable {

    private int userId;
    private String name;
    private boolean isMale;

    public User(int userId, String name, boolean isMale) {
        this.userId = userId;
        this.name = name;
        this.isMale = isMale;
    }

    private User(Parcel in) {
        this.userId = in.readInt();
        this.name = in.readString();
        this.isMale = in.readInt() == 1;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(name);
        dest.writeInt(isMale ? 1 : 0);
    }

    @Override
    public String toString() {
        return "User - id : " + userId + " name : " + name + " isMale : " + isMale;
    }
}
