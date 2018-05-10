package com.lucidastar.chapter_10;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HandlerStudyActivity extends AppCompatActivity {

    private Handler mHandler;
    private static final String TAG = "HandlerStudyActivity";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_study);
        mTextView = this.findViewById(R.id.tv_rec);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: 收到了消息"+simpleDateFormat.format(new Date(System.currentTimeMillis())));
            }
        };
    }

    public void sendHandlerMessage(View view) {

        Log.d(TAG, "sendHandlerMessage: "+ simpleDateFormat.format(new Date(System.currentTimeMillis())));
        Message msg = Message.obtain();
        mHandler.sendMessageDelayed(msg,5000);
    }

    public void sendHandlerMessage2(View view) {
        Log.d(TAG, "sendHandlerMessage2: "+ simpleDateFormat.format(new Date(System.currentTimeMillis())));
        Message msg = Message.obtain();
        mHandler.sendMessageDelayed(msg,1000);
    }

    public void sendMessageInNewThread(View view){
        new Thread(new Runnable() {//
            @Override
            public void run() {
                Looper.prepare();
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {//会挂掉，因为更新UI不是在主线程
                        mTextView.setText("收到了消息");
                    }
                }.sendEmptyMessage(0);
                Looper.loop();
            }
        }).start();
    }
}
