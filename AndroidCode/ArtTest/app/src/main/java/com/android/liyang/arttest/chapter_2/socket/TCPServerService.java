package com.android.liyang.arttest.chapter_2.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.android.liyang.arttest.chapter_2.util.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by liyang on 17/12/8.
 */

public class TCPServerService extends Service {

    private static final String TAG = "chapter_2_socket_server";

    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
            "你好啊,哈哈",
            "请问你叫什么名字啊?",
            "今天北京天气不错啊,shy",
            "你知道吗?我可是可以和多个人同时聊天的哦",
            "给你讲个笑话吧:据说爱笑的人运气不会太差,不知道真假。"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                // 监听本地8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    Log.i(TAG, "accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接受客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
        out.println("欢迎来到聊天室");
        while (!mIsServiceDestroyed) {
            String str = in.readLine();
            Log.i(TAG, "msg from client:" + str);
            if (TextUtils.isEmpty(str)) {
                // 客户端断开链接
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            Log.i(TAG, "send:" + msg);
        }
        Log.i(TAG, "client quit.:");
        // 关闭流
        MyUtils.close(out);
        MyUtils.close(in);
        client.close();
    }
}
