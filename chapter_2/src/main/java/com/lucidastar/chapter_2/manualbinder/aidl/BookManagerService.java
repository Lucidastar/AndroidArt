package com.lucidastar.chapter_2.manualbinder.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
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

//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();
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
           /* if (mListeners.contains(listener)){
                Log.d(TAG, "already exists. ");
            }else {
                Log.d(TAG, "add new listener. ");
                mListeners.add(listener);
            }*/
           mListeners.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*if (mListeners.contains(listener)){
                mListeners.remove(listener);
                Log.d(TAG, "unregister listener succeed ");
            }else {
                Log.d(TAG, "not found , can not unregister ");
            }*/
            mListeners.unregister(listener);
            Log.d(TAG, "unregister listener succeed ");
            Log.d(TAG, "unregisterListener current size : " +mListeners.getRegisteredCallbackCount());
        }
    };
    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.lucidastar.chapter_2.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED){
            Log.d(TAG, "onBind: 没有权限");
            return null;
        }
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
        int N = mListeners.beginBroadcast();
        for (int i = 0 ; i < N;i++){
            IOnNewBookArrivedListener onNewBookArrivedListener = mListeners.getBroadcastItem(i);

//            onNewBookArrivedListener.onNewBookArrived(book);

            if (onNewBookArrivedListener != null){
                onNewBookArrivedListener.onNewBookArrived(book);
                Log.i(TAG, "onNewBookArrived, notify listener:"+onNewBookArrivedListener);
            }
        }
        mListeners.finishBroadcast();
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
                    Log.i(TAG, "run: "+newBook.toString());
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
