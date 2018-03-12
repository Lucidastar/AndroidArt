package com.lucidastar.chapter_2.provider;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucidastar.chapter_2.R;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Uri uri = Uri.parse("content://com.lucidastar.chapter_2.book.provider");
        getContentResolver().query(uri,null,null,null,null,null);
        getContentResolver().query(uri,null,null,null,null,null);
        getContentResolver().query(uri,null,null,null,null,null);
        getContentResolver().query(uri,null,null,null,null,null);
        getContentResolver().query(uri,null,null,null,null,null);
        getContentResolver().query(uri,null,null,null,null,null);

    }
}
