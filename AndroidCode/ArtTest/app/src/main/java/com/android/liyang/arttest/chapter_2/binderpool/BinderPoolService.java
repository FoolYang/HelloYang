package com.android.liyang.arttest.chapter_2.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.liyang.arttest.chapter_2.binderpool.aidl.impl.BinderPoolIml;

/**
 * Created by liyang on 17/12/9.
 */

public class BinderPoolService extends Service {

    private static final String TAG = "chapter_2_binder_pool";

    private Binder mBinderPool = new BinderPoolIml();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }


}
