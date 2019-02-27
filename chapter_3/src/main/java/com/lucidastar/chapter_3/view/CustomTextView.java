package com.lucidastar.chapter_3.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/10/16.
 */

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        Logger.i("dispatchTouchEvent_custom");
        showFilter(event,"dispatchTouchEvent","custom");
        return  true;
//        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Logger.i("onTouchEvent_custom");
        showFilter(event,"onTouchEvent","custom");
        return true;
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
