package com.lucidastar.chapter_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lucidastar.chapter_3.view.Logger;

public class DemoActivity_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_3);
    }

    public void TextViewOnClick(View view) {
        Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showFilter(event,"onTouchEvent","Activity");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        showFilter(event,"dispatchTouchEvent","Activity");
        return super.dispatchTouchEvent(event);
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
