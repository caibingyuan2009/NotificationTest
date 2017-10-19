package com.test.notification.notificationtest.util;

import android.os.Build;

/**
 * Created by caibingyuan on 2017/10/19.
 */

public class BuildVersionCheckUtils {

    /**
     * 判断是否低于Android 8.0
     * @return
     */
    public static boolean isUnderAndroidO() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.O;
    }
}
