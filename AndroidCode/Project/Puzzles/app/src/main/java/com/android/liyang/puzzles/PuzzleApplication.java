package com.android.liyang.puzzles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by liyang on 17/9/15.
 */

public class PuzzleApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Log.i("yang_puzzles", "init application");
    }


    public static Context getContext() {
        return mContext;
    }

}
