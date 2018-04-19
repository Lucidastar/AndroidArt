package com.lucidastar.chapter_4;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            int width = mView.getMeasuredWidth();
            int height = mView.getMeasuredHeight();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.post(new Runnable() {
            @Override
            public void run() {
                int width = mView.getMeasuredWidth();
                int height = mView.getMeasuredHeight();
            }
        });

        ViewTreeObserver viewTreeObserver = mView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = mView.getMeasuredWidth();
                int height = mView.getMeasuredHeight();
            }
        });
    }
}
