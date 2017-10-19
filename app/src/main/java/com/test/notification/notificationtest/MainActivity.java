package com.test.notification.notificationtest;

import android.app.Notification;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.test.notification.notificationtest.notification.NotificationHelper;
import com.test.notification.notificationtest.statistics.NotificationConstant;
import com.test.notification.notificationtest.util.NotificationPermissionUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mPrimaryNotificationTitleEt;
    private Button mPrimaryLeftBtn;
    private Button mPrimaryRightBtn;
    private ImageButton mPrimaryConfigIb;

    private EditText mSecondaryNotificationTitleEt;
    private Button mSecondaryLeftBtn;
    private Button mSecondaryRightBtn;
    private ImageButton mSecondaryConfigIb;

    private Button mSettingJumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        boolean isEnable = NotificationPermissionUtils.isNotificationEnabled(this);
        Toast.makeText(this, "通知栏开启状态： isEnable = " + isEnable, Toast.LENGTH_LONG).show();
    }

    private void initView() {
        mPrimaryNotificationTitleEt = (EditText) findViewById(R.id.main_primary_title);
        mPrimaryLeftBtn = (Button) findViewById(R.id.main_primary_send1);
        mPrimaryRightBtn = (Button) findViewById(R.id.main_primary_send2);
        mPrimaryConfigIb = (ImageButton) findViewById(R.id.main_primary_config);

        mSecondaryNotificationTitleEt = (EditText) findViewById(R.id.main_secondary_title);
        mSecondaryLeftBtn = (Button) findViewById(R.id.main_secondary_send1);
        mSecondaryRightBtn = (Button) findViewById(R.id.main_secondary_send2);
        mSecondaryConfigIb = (ImageButton) findViewById(R.id.main_secondary_config);

        mSettingJumpBtn = (Button) findViewById(R.id.setting_jump_btn);

        mPrimaryLeftBtn.setOnClickListener(this);
        mPrimaryRightBtn.setOnClickListener(this);
        mSecondaryLeftBtn.setOnClickListener(this);
        mSecondaryRightBtn.setOnClickListener(this);
        mPrimaryConfigIb.setOnClickListener(this);
        mSecondaryConfigIb.setOnClickListener(this);
        mSettingJumpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Notification.Builder builder;
        switch (view.getId()) {
            case R.id.main_primary_send1:
                builder = NotificationHelper.createNotificationBuilder(getPrimaryNotificationTitle(),
                        "第一个渠道的通知内容：我是第一个渠道的第一个通知", NotificationConstant.CHANNEL_ID_PRIMARY);
                NotificationHelper.getInstance(this).sendNotification(NotificationConstant.NOTIFICATION_ID_1,
                        builder);
                break;
            case R.id.main_primary_send2:
                builder = NotificationHelper.createNotificationBuilder(getPrimaryNotificationTitle(),
                        "第一个渠道的通知内容：我是第一个渠道的第二个通知", NotificationConstant.CHANNEL_ID_PRIMARY);
                NotificationHelper.getInstance(this).sendNotification(NotificationConstant.NOTIFICATION_ID_2,
                        builder);
                break;
            case R.id.main_secondary_send1:
                builder = NotificationHelper.createNotificationBuilder(getSecondaryNotificationTitle(),
                        "第二个渠道的通知内容：我是第二个渠道的第一个通知", NotificationConstant.CHANNEL_ID_SECONDARY);
                NotificationHelper.getInstance(this).sendNotification(NotificationConstant.NOTIFICATION_ID_3,
                        builder);
                break;
            case R.id.main_secondary_send2:
                builder = NotificationHelper.createNotificationBuilder(getSecondaryNotificationTitle(),
                        "第二个渠道的通知内容：我是第二个渠道的第二个通知", NotificationConstant.CHANNEL_ID_SECONDARY);
                NotificationHelper.getInstance(this).sendNotification(NotificationConstant.NOTIFICATION_ID_4,
                        builder);
                break;
            case R.id.main_primary_config:
                goToNotificationSettings(NotificationConstant.CHANNEL_ID_PRIMARY);
                Toast.makeText(this, "跳转到第一个渠道的通知设置页，可以开启或关闭这种渠道的通知", Toast.LENGTH_LONG).show();
                break;
            case R.id.main_secondary_config:
                goToNotificationSettings(NotificationConstant.CHANNEL_ID_SECONDARY);
                Toast.makeText(this, "跳转到第二个渠道的通知设置页，可以开启或关闭这种渠道的通知", Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_jump_btn:
                goToNotificationSettings();
                Toast.makeText(this, "跳转到通知栏设置页，可以开启或者关闭所有渠道的通知", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private CharSequence getPrimaryNotificationTitle() {
        CharSequence title = mPrimaryNotificationTitleEt.getText();
        if (title == null || title.length() == 0) {
            return "Primary Notification Title";
        }

        return title;
    }

    private CharSequence getSecondaryNotificationTitle() {
        CharSequence title = mSecondaryNotificationTitleEt.getText();
        if (title == null || title.length() == 0) {
            return "Secondary Notification Title";
        }

        return title;
    }

    public void goToNotificationSettings(String channel) {
        Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        startActivity(i);
    }

    public void goToNotificationSettings() {
        Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(i);
    }
}
