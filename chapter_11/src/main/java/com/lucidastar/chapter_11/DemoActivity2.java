package com.lucidastar.chapter_11;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoActivity2 extends AppCompatActivity {

    private static final String TAG = "DemoActivity2";
    private int id = 0;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        mTextView = findViewById(R.id.tv_test);
    }

    public void testAsyncTask(View view) {
//        new MyAsyncTask("ceshi").execute("");
//        new MyAsyncTask("ceshi").execute("");
//        new MyAsyncTask("ceshi").execute("");
//        new MyAsyncTask("ceshi").execute("");
        int testNum = 80;
        MyAsyncTask myAsyncTask = new MyAsyncTask("ceshi"+testNum);
        String[] params = new String[testNum];
        int i = 0;
        while (testNum != 0){
            params[i++] = "ceshi"+testNum;
                testNum -- ;
        }
        myAsyncTask.execute(params);
    }

    private class MyAsyncTask extends AsyncTask<String,Integer,String> {
        private String mName = "AsyncTask";

        public MyAsyncTask(String name) {
            super();
            mName = name;
        }
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: "+mName);
        }
        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: "+s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Log.d(TAG, "onPostExecute: "+dateFormat.format(new Date()));
//            mTextView.setText(mName);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "onProgressUpdate: "+values[0]);
            Log.d(TAG, "onProgressUpdate长度: "+values.length);
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
            Log.d(TAG, "doInBackground: "+strings[0]);
            for (int i = 0; i < strings.length; i++) {
                publishProgress(id++);
            }
           /* try {
//                Thread.sleep(2000);
                publishProgress(id++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            return mName;
        }
    }
}
