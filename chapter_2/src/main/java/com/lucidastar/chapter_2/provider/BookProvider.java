package com.lucidastar.chapter_2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by qiuyouzone on 2018/3/12.
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = "BookProvider";
    private Context mContext;
    private DbOpenHelper mDbOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;
    public static final String AUTHORITY = "com.lucidastar.chapter_2.book.provider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ,current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData();//正式项目中不能在主线程中执行数据库操作，因为有可能会阻塞主线程
        return true;
    }

    private void initProviderData(){
        mSQLiteDatabase = new DbOpenHelper(mContext).getWritableDatabase();
        mSQLiteDatabase.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mSQLiteDatabase.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);

        mSQLiteDatabase.execSQL("insert into book values (3,'android');");
        mSQLiteDatabase.execSQL("insert into book values (4,'IOS');");
        mSQLiteDatabase.execSQL("insert into book values (5,'Html');");
        mSQLiteDatabase.execSQL("insert into user values (1,'smith',1);");
        mSQLiteDatabase.execSQL("insert into user values (2,'jack',2);");
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query , current thread :" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        Cursor cursor = mSQLiteDatabase.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: ");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        mSQLiteDatabase.insert(table,null,values);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        int delete = mSQLiteDatabase.delete(table, selection, selectionArgs);
        if (delete > 0){
            mContext.getContentResolver().notifyChange(uri,null);
        }
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update: ");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        int update = mSQLiteDatabase.update(table, values, selection, selectionArgs);
        if (update > 0){
            mContext.getContentResolver().notifyChange(uri,null);
        }
        return update;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
        }

        return tableName;
    }
}
