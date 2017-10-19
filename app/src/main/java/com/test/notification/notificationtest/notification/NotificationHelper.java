package com.test.notification.notificationtest.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.widget.Toast;

import com.test.notification.notificationtest.NotificationTestApplication;
import com.test.notification.notificationtest.statistics.NotificationConstant;
import com.test.notification.notificationtest.util.BuildVersionCheckUtils;

/**
 * Created by caibingyuan on 2017/10/19.
 */

public class NotificationHelper extends ContextWrapper {
    private NotificationManager manager;

    private static NotificationHelper sNotificationManager;

    public static NotificationHelper getInstance(Context context) {
        if (sNotificationManager == null) {
            sNotificationManager = new NotificationHelper(context);
        }

        return sNotificationManager;
    }

    public static Notification.Builder createNotificationBuilder(CharSequence title, String body, String notificationChannelId) {
        if (BuildVersionCheckUtils.isUnderAndroidO()) {
            return null;
        }

        Notification.Builder notificationBuilder =  new Notification.Builder(NotificationTestApplication.getApplication(), notificationChannelId)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true);

        return notificationBuilder;
    }

    public void sendNotification(int id, Notification.Builder builder) {
        if (BuildVersionCheckUtils.isUnderAndroidO()) {
            Toast.makeText(NotificationTestApplication.getApplication(), "手机版本低于8.0， 不能弹出通知栏", Toast.LENGTH_LONG).show();
            return;
        }

        if (builder == null) {
            return;
        }

        getManager().notify(id, builder.build());
    }

    public static int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }

    private NotificationHelper(Context context) {
        super(context);
        initNotificationChannel();
    }

    private void initNotificationChannel() {
        if (BuildVersionCheckUtils.isUnderAndroidO()){
            return;
        }

        // 初始化第一个频道
        NotificationChannel channelPrimary = new NotificationChannel(NotificationConstant.CHANNEL_ID_PRIMARY,
                NotificationConstant.CHANNEL_NAME_PRIMARY, NotificationManager.IMPORTANCE_DEFAULT);
        channelPrimary.setLightColor(Color.GREEN);
        channelPrimary.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channelPrimary);

        // 初始化第二个频道
        NotificationChannel channelSecondary = new NotificationChannel(NotificationConstant.CHANNEL_ID_SECONDARY,
                NotificationConstant.CHANNEL_NAME_SECONDARY, NotificationManager.IMPORTANCE_HIGH);
        channelSecondary.setLightColor(Color.BLUE);
        channelSecondary.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(channelSecondary);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

}
