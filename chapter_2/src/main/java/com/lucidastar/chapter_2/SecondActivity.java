package com.lucidastar.chapter_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lucidastar.chapter_2.model.User;
import com.lucidastar.chapter_2.utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = (User) getIntent().getSerializableExtra("extra_user");
//        User user = getIntent().getParcelableExtra("extra_user");
        Log.d(TAG, "user:" + user.toString());
        // Log.d(TAG, "UserManage.sUserId=" + UserManager.sUserId);
        recoverFromFile();
    }

    private void recoverFromFile() {
        User user = null;
        ObjectInputStream objectInputStream = null;
        File file = new File(Constants.CACHE_FILE_PATH);
        if (file.exists()){
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                user = (User) objectInputStream.readObject();
                Log.d(TAG, "recoverFromFile: "+user);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
