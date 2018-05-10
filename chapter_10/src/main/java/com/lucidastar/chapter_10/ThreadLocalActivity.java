package com.lucidastar.chapter_10;

import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThreadLocalActivity extends AppCompatActivity {

    private static final String TAG = "ThreadLocalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local);
        openNewMethod();
    }
    private void openNewMethod() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        Log.d(TAG, "默认获取 "+threadLocal.get());
        threadLocal.set("这是主线程"+ Thread.currentThread().getName());
        Log.d(TAG, "在主线程中获取： "+threadLocal.get());
        startNewThread(threadLocal);
    }
    private void startNewThread(final ThreadLocal<String> threadLocal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("这是第一个子线程"+Thread.currentThread().getName());
                Log.d(TAG, "在子线程中获取："+threadLocal.get());
            }
        }).start();
    }


}
