package com.android.liyang.arttest.chapter_2.provider;

/**
 * Created by liyang on 17/12/7.
 */

public class User {
    public int userId;
    public String userName;
    public boolean isMale;

    @Override
    public String toString() {
        return "userId:" + userId + " , userName:" + userName + " , isMale" + isMale;
    }
}
