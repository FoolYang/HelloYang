package com.android.liyang.eventbus;

/**
 * Created by liyang on 17/8/5.
 */

public class FirstEvent {

    private String mMsg;

    public FirstEvent(String msg) {
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }
}
