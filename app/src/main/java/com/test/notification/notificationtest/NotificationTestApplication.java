package com.test.notification.notificationtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by caibingyuan on 2017/10/19.
 */

public class NotificationTestApplication extends Application {
    private static Context sNewsApplication;
    /**
     * 获取Application
     */
    public static Context getApplication() {
        return sNewsApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sNewsApplication = this;
    }

}
