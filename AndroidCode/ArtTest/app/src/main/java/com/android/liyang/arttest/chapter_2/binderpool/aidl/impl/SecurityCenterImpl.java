package com.android.liyang.arttest.chapter_2.binderpool.aidl.impl;

import android.os.RemoteException;

import com.android.liyang.arttest.chapter_2.binderpool.ISecurityCenter;

/**
 * Created by liyang on 17/12/9.
 *
 *
 * 提供加密和解密功能
 *
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
