package com.lucidastar.chapter_5;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.lucidastar.chapter_5.utils.MyConstants;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private LinearLayout mRemoteViewsContent;

    private BroadcastReceiver mRemoteViewsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent
                    .getParcelableExtra(MyConstants.EXTRA_REMOTE_VIEWS);
            if (remoteViews != null) {
                updateUI(remoteViews);
            }
        }
    };

    private void initView() {
        mRemoteViewsContent = (LinearLayout) findViewById(R.id.remote_views_content);
        IntentFilter filter = new IntentFilter(MyConstants.REMOTE_ACTION);
        registerReceiver(mRemoteViewsReceiver, filter);
    }

    private void updateUI(RemoteViews remoteViews) {
//        View view = remoteViews.apply(this, mRemoteViewsContent);
        int layoutId = getResources().getIdentifier("layout_simulated_notification", "layout", getPackageName());
        View view = getLayoutInflater().inflate(layoutId, mRemoteViewsContent, false);
        remoteViews.reapply(this, view);
        mRemoteViewsContent.addView(view);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mRemoteViewsReceiver);
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        openNotification();
        initView();
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
        builder.setTicker("ticker")
                .setContentText("contentText")
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


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

    }

    public void customNotification(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"chaper_5");
        builder
                .setSmallIcon(R.mipmap.ic_launcher);
//                .setTicker("ticker")
//                .setContentText("contentText")
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle("contentTitle")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults( Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND )
//                .setTicker("悬浮通知");

//        Intent intent = new Intent(this,DemoActivity_2.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
        remoteViews.setTextViewText(R.id.msg,"chapter_5");
        remoteViews.setImageViewResource(R.id.icon,R.drawable.icon1);
        PendingIntent openActivity2pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,DemoActivity_2.class),PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2,openActivity2pendingIntent);
        builder.setCustomContentView(remoteViews);
        builder.setContentIntent(openActivity2pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());
    }

    public void openDemo2(View view) {
        Intent intent = new Intent(this, DemoActivity_2.class);
        startActivity(intent);
    }
}
