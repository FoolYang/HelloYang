package com.android.liyang.arttest.chapter_2.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.android.liyang.arttest.chapter_2.util.MyConstants;

/**
 * Created by liyang on 17/11/25.
 */

public class MessengerService extends Service {

    private static final String TAG = MessengerService.class.getSimpleName();


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i(TAG, "receive msg from client:" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replayMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "嗯, 你的消息我已经收到,稍后会回复你");
                    replayMessage.setData(bundle);
                    try {
                        client.send(replayMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);

            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
