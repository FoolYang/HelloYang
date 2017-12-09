package com.android.liyang.arttest.chapter_2.binderpool.aidl.impl;

import android.os.IBinder;
import android.os.RemoteException;

import com.android.liyang.arttest.chapter_2.binderpool.IBinderPool;

import static com.android.liyang.arttest.chapter_2.binderpool.BinderPool.BINDER_COMPUTE;
import static com.android.liyang.arttest.chapter_2.binderpool.BinderPool.BINDER_SECURITY_CENTER;

/**
 * Created by liyang on 17/12/9.
 */

public class BinderPoolIml extends IBinderPool.Stub {
    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;
        switch (binderCode) {
            case BINDER_SECURITY_CENTER:
                binder = new SecurityCenterImpl();
                break;
            case BINDER_COMPUTE:
                binder = new ComputeImpl();
                break;
            default:
                break;
        }
        return binder;
    }
}
