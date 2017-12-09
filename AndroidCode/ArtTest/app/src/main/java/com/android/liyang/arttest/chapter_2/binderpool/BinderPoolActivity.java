package com.android.liyang.arttest.chapter_2.binderpool;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.android.liyang.arttest.R;
import com.android.liyang.arttest.chapter_2.binderpool.aidl.impl.ComputeImpl;
import com.android.liyang.arttest.chapter_2.binderpool.aidl.impl.SecurityCenterImpl;

/**
 * Created by liyang on 17/12/9.
 */

public class BinderPoolActivity extends Activity {

    private static final String TAG = "chapter_2_binder_pool";
    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_2_binder_pool_activity);
        new Thread() {
            @Override
            public void run() {
                doWork();
            }
        }.start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);

        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);

        Log.i(TAG, "visit ISecurityCenter");
        String msg = "hello world - 安卓";
        Log.i(TAG, msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.i(TAG, "encrypt password:" + password);
            Log.i(TAG, "decrypt password:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            Log.i(TAG, " 3 + 5 = " + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
