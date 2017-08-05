package com.android.liyang.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;


public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewClick();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(FirstEvent event) {
        Log.i("shaa", "onEventMainThread收到了消息:" + event.getMsg());
        mTextView.setText(event.getMsg());
        Toast.makeText(this, event.getMsg(), Toast.LENGTH_LONG).show();
    }

    public void onEventMainThread() {
        Log.i("shaa", "onEventMainThread收到了空消息:");
    }

    public void onEvent(FirstEvent event) {
        Log.i("shaa", "onEventMainThread收到了  onEvent:");
    }

    public void onEventBackgroundThread(FirstEvent event) {
        Log.i("shaa", "onEventMainThread收到了  onEventBackground:");
    }

    public void onEventAsync(FirstEvent enent) {
        Log.i("shaa", "onEventMainThread收到了  onEventAsync:");
    }

    private void initViewClick() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text);
    }
}
