package com.lucidastar.chapter_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
//            Intent intent = new Intent(this, TestActivity.class);
//            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
//            Intent intent = new Intent(this, DemoActivity_1.class);
//            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
//            Intent intent = new Intent(this, DemoActivity_2.class);
//            startActivity(intent);
        }else if (v.getId() == R.id.tv_test){

        }
    }

}
