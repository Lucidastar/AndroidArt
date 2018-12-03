package com.lucidastar.chapter_3.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/10/16.
 */

public class LayoutC extends RelativeLayout {

    private final String TAG = LayoutC.this.getClass().getName();

    public LayoutC(Context context) {
        super(context);
    }

    public LayoutC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {//分发
//        Logger.i("dispatchTouchEvent_C");
//        showFilter(ev,"dispatchTouchEvent","C");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//拦截
//        Logger.i("onInterceptTouchEvent_C");
        showFilter(ev,"onInterceptTouchEvent","C");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//消费
//        Logger.i("onTouchEvent_C");
        showFilter(event,"onTouchEvent","C");
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
