package com.lucidastar.chapter_2.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lucidastar.chapter_2.R;
import com.lucidastar.chapter_2.utils.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int MESSAGE_RECEIVE_NEW_MSG = 1;
    private final static int MESSAGE_SOCKET_CONNECTED = 2;
    private final static int MESSAGE_SOCKET_SEND = 3;

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mMessageTextView.setText(mMessageTextView.getText() + (String) msg.obj);
                    break;

                case MESSAGE_SOCKET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                case MESSAGE_SOCKET_SEND:
                    mMessageEditText.setText("");
                    String time = formatDateTime(System.currentTimeMillis());
                    String showedMsg = "self"+ time + ":"+msg.obj+"\n";
                    mMessageTextView.setText(mMessageTextView.getText().toString()+showedMsg);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        mMessageTextView = this.findViewById(R.id.msg_container);
        mSendButton = this.findViewById(R.id.send);
        mSendButton.setOnClickListener(this);
        mMessageEditText = this.findViewById(R.id.msg);

        Intent intent = new Intent(this,TCPServerService.class);
        startService(intent);

        new Thread(){
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null){
            try {
                socket = new Socket("localhost",8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed ,retry...");
                e.printStackTrace();
            }
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()){
                String msg = bufferedReader.readLine();
                System.out.println("receive : "+msg);
                if (msg != null){
                    String time = formatDateTime(System.currentTimeMillis());
                    String showedMsg = "server"+ time + ":"+msg+"\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG,showedMsg).sendToTarget();
                }
            }

            MyUtils.close(mPrintWriter);
            MyUtils.close(bufferedReader);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mSendButton){
            new Thread(){
                @Override
                public void run() {
                    String msg = mMessageEditText.getText().toString();
                    if (!TextUtils.isEmpty(msg) && mPrintWriter != null){
                        mPrintWriter.println(msg);
                        mHandler.obtainMessage(MESSAGE_SOCKET_SEND,msg).sendToTarget();
                    }
                }
            }.start();

        }
    }

    private String formatDateTime(long time){
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null){
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onDestroy();
    }
}
