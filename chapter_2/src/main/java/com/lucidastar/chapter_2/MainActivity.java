package com.lucidastar.chapter_2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lucidastar.chapter_2.model.Book;
import com.lucidastar.chapter_2.model.User;
import com.lucidastar.chapter_2.utils.Constants;
import com.lucidastar.chapter_2.utils.MyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persistToFile();
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

    private void persistToFile(){
        new Thread(){
            @Override
            public void run() {
                User user = new User(1,"hello word",false);
                File file = new File(Constants.CHAPTER_2_PATH);
                if (!file.exists()){
                    file.mkdirs();
                }

                File cacheFile = new File(Constants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheFile));
                    objectOutputStream.writeObject(user);
                    Log.d(TAG, "persist user:"+user);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    MyUtils.close(objectOutputStream);
                }
            }
        }.start();
    }
}
