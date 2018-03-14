package com.lucidastar.chapter_2.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lucidastar.chapter_2.utils.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by qiuyouzone on 2018/3/14.
 */

public class TCPServerService extends Service{

    private boolean mIsServiceDestroy = false;
    private String[] mDefinedMessage = new String[]{"你好啊，哈哈",
            "请问你叫什么名字啊？",
            "今天北京天气不错啊",
            "我可以跟很多人说话",
            "我会讲小笑话"
    };
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TCPServer()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroy = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TCPServer implements Runnable{

        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(8688);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestroy){
                try {
                    final Socket client = serverSocket.accept();
                    System.out.print("accept");
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        //接受客户端信息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream ()));

        //向客户端发送消息
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        printWriter.println("欢迎来到聊天室");

        while (!mIsServiceDestroy){
            String str = bufferedReader.readLine();
            System.out.println("msg from client:"+str);
            if (str == null){
                break;
            }
            int i = new Random().nextInt(mDefinedMessage.length);
            String msg = mDefinedMessage[i];
            printWriter.println(msg);
            System.out.println("send : "+msg);
        }

        System.out.println("client quit.");
        MyUtils.close(printWriter);
        MyUtils.close(bufferedReader);
        client.close();
    }
}
