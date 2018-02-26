package com.lucidastar.chapter_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lucidastar.chapter_2.model.User;

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
//        recoverFromFile();
    }
}
