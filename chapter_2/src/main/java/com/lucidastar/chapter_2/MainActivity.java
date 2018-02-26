package com.lucidastar.chapter_2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lucidastar.chapter_2.model.Book;
import com.lucidastar.chapter_2.model.User;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                User user = new User(0, "jake", true);
                user.mBook = new Book();
                intent.putExtra("extra_user", (Serializable) user);
                startActivity(intent);
            }
        });

    }
}
