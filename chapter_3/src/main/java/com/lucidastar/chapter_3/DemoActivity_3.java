package com.lucidastar.chapter_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DemoActivity_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_3);
    }

    public void TextViewOnClick(View view) {
        Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
    }
}
