package com.lucidastar.chapter_5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        openNotification();
    }

    private void openNotification() {
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this,DemoActivity_1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pendingIntent;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification);


    }

    private void openNotificationNew() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"chaper_5");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("ticker").setContentText("contentText")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("contentTitle")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults( Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND )
                .setTicker("悬浮通知");

        Intent intent = new Intent(this,DemoActivity_1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }

    public void openNotifi(View view) {
        openNotification();
    }

    public void openNotifi_1(View view) {
        openNotificationNew();
    }
}
