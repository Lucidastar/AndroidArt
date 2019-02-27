package com.lucidastar.chapter_3.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/10/16.
 */

public class LayoutB extends RelativeLayout {

    private final String TAG = LayoutB.this.getClass().getName();

    public LayoutB(Context context) {
        super(context);
    }

    public LayoutB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {//分发
//        Logger.i("dispatchTouchEvent_B");
        showFilter(ev,"dispatchTouchEvent","B");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//拦截
//        Logger.i("onInterceptTouchEvent_B");
        showFilter(ev,"onInterceptTouchEvent","B");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//消费
//        Logger.i("onTouchEvent_B");
        showFilter(event,"onTouchEvent","B");
        return super.onTouchEvent(event);
    }

    private void showFilter(MotionEvent event,String pa,String pb) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.i(pa+"_ACTION_DOWN_"+pb);
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.i(pa+"_ACTION_MOVE_"+pb);
                break;
            case MotionEvent.ACTION_UP:
                Logger.i(pa+"_ACTION_UP_"+pb);
                break;
        }
    }
}
