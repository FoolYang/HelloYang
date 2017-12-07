package com.android.liyang.arttest.chapter_2.socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.liyang.arttest.R;
import com.android.liyang.arttest.chapter_2.util.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyang on 17/12/8.
 */

public class TCPClientActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "chapter_2_socket_client";

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private Button mSendButton;
    private EditText mMessageEditText;
    private TextView mMessageTextView;

    private PrintWriter mPrintWriter;
    private Socket mClintSorket;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mMessageTextView.setText(mMessageTextView.getText() + (String)msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_2_socket_activity);
        mMessageTextView = (TextView) findViewById(R.id.chapter_2_socket_txt);
        mSendButton = (Button) findViewById(R.id.chapter_2_socket_btn);
        mMessageEditText = (EditText) findViewById(R.id.chapter_2_socket_edit);
        mSendButton.setOnClickListener(this);

        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (mClintSorket != null) {
            try {
                mClintSorket.shutdownInput();
                mClintSorket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == mSendButton) {
            final String msg = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                mPrintWriter.println(msg);
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self " + time + ":" + msg + "\n";
                mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
            }
        }
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClintSorket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                Log.i(TAG, "connect server success");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // 接收服务端的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (!TCPClientActivity.this.isFinishing()) {
                    String msg = br.readLine();
                    Log.i(TAG, "receive:" + msg);
                    if (!TextUtils.isEmpty(msg)) {
                        String time = formatDateTime(System.currentTimeMillis());
                        final String showedMsg = "server" + time + ":" + msg + "\n";
                        mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                    }
                }
                Log.i(TAG, "quit...");
                MyUtils.close(mPrintWriter);
                MyUtils.close(br);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
