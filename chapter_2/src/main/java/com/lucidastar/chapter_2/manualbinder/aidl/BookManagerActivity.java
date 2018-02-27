package com.lucidastar.chapter_2.manualbinder.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lucidastar.chapter_2.IBookManager;
import com.lucidastar.chapter_2.R;
import com.lucidastar.chapter_2.model.Book;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BookManagerActivity";
    private IBookManager mRemoteBookManager;
    private static final int MSG_NEW_BOOK_ARRIVE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(this,BookManagerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);

            try {
                mRemoteBookManager = bookManager;
                List<Book> bookList = bookManager.getBookList();
                Log.i(TAG, "query book list ,list type : "+bookList.getClass().getCanonicalName());
                Log.i(TAG, "book list: "+bookList.toString());

                Book book = new Book(3,"Android 艺术探索");
                bookManager.addBook(book);
                Log.i(TAG, "add book:"+book);

                List<Book> newBookList = bookManager.getBookList();
                Log.i(TAG, "new book list: "+newBookList.toString());

//                bookManager.registerListener();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_NEW_BOOK_ARRIVE:
                    Log.d(TAG, "receive new book :" + msg.obj);
                    break;
                    default:
                        super.handleMessage(msg);
            }
        }
    };
}
