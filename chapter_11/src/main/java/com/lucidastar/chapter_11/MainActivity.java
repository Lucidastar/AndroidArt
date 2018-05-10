package com.lucidastar.chapter_11;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAsyncTaskTest(View view) {
//        new MyAsyncTask("AsyncTask#1").execute("");
//        new MyAsyncTask("AsyncTask#2").execute("");
//        new MyAsyncTask("AsyncTask#3").execute("");
//        new MyAsyncTask("AsyncTask#4").execute("");
//        new MyAsyncTask("AsyncTask#5").execute("");
        new MyAsyncTask("AsyncTask#6").execute("");

        new MyAsyncTask("AsyncTask#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#6").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
    }

    public void openActivity2(View view) {
        startActivity(new Intent(this,DemoActivity2.class));
    }

    private class MyAsyncTask extends AsyncTask<String,Integer,String>{

        private String mName = "AsyncTask";

        public MyAsyncTask(String name) {
            super();
            mName = name;
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: "+s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.d(TAG, "onPostExecute: "+dateFormat.format(new Date()));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "onProgressUpdate: ");
        }

        @Override
        protected void onCancelled(String s) {
            Log.d(TAG, "onCancelled: ");
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled: ");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: "+strings);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }
    }
}
