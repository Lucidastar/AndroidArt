package com.lucidastar.chapter_2.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lucidastar.chapter_2.R;
import com.lucidastar.chapter_2.model.Book;
import com.lucidastar.chapter_2.model.User;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

//        Uri uri = Uri.parse("content://com.lucidastar.chapter_2.book.provider");
//        getContentResolver().query(uri,null,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null,null);

        Uri bookUri = Uri.parse("content://com.lucidastar.chapter_2.book.provider/book");
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",10);
        contentValues.put("name","程序设计");
        getContentResolver().insert(bookUri,contentValues);

        Cursor query = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (query.moveToNext()){
            Book book = new Book();
            int bookId = query.getInt(0);
            String bookName = query.getString(1);
            book.bookId = bookId;
            book.bookName = bookName;
            Log.d(TAG, "query book: "+book.toString());
        }
        query.close();


        Uri userUri = Uri.parse("content://com.lucidastar.chapter_2.book.provider/user");

        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor.moveToFirst()){
            while (userCursor.moveToNext()){
                User user = new User();
                int userId = userCursor.getInt(0);
                String userName = userCursor.getString(1);
                boolean isMale = userCursor.getInt(2) == 1;
                user.userId = userId;
                user.userName = userName;
                user.isMale = isMale;

                Log.d(TAG, "query user:"+user.toString());
            }
        }
    }
}
