package com.lucidastar.chapter_2.manualbinder.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lucidastar.chapter_2.IBookManager;
import com.lucidastar.chapter_2.IOnNewBookArrivedListener;
import com.lucidastar.chapter_2.model.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {
    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestroy = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (mListeners.contains(listener)){
                Log.d(TAG, "already exists. ");
            }else {
                mListeners.add(listener);
            }
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (mListeners.contains(listener)){
                mListeners.remove(listener);
                Log.d(TAG, "unregister listener succeed ");
            }else {
                Log.d(TAG, "not found , can not unregister ");
            }
            Log.d(TAG, "unregisterListener current size : " +mListeners.size());
        }
    };
    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"android"));
        mBookList.add(new Book(2,"java"));
        new Thread(new ServiceWork()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroy.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);

        for (int i = 0 ; i < mListeners.size();i++){
            IOnNewBookArrivedListener onNewBookArrivedListener = mListeners.get(i);
            Log.i(TAG, "onNewBookArrived, notify listener:"+onNewBookArrivedListener);
            onNewBookArrivedListener.onNewBookArrived(book);
        }
    }

    private class ServiceWork implements Runnable{

        @Override
        public void run() {
            while (!mIsServiceDestroy.get()){
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() +1;
                Book newBook = new Book(bookId,"newBook #"+bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
