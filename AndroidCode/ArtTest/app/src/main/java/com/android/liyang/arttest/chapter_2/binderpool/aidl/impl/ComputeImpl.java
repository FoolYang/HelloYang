package com.android.liyang.arttest.chapter_2.binderpool.aidl.impl;

import android.os.RemoteException;

import com.android.liyang.arttest.chapter_2.binderpool.ICompute;

/**
 * Created by liyang on 17/12/9.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
