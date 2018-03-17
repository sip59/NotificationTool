package com.zozo.notificationtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Unbinder mUnbinder;

    private static final int NOTIFY_ID_PULL_DOWN = 0x1;
    private static final int NOTIFY_ID_HEADSUP = 0x2;
    private static final int NOTIFY_ID_CUSTOM = 0x3;
    private static final int NOTIFY_ID_EXPAND_PULL_DOWN = 0x4;
    private static final int NOTIFY_ID_EXPAND_HEADSUP = 0x5;
    private static final int NOTIFY_ID_EXPAND_CUSTOM = 0x6;

    @BindView(R.id.pull_down) Button mPullDown;
    @BindView(R.id.headsup) Button mHeadsup;
    @BindView(R.id.custom) Button mCustom;
    @BindView(R.id.expand_pull_down) Button mExpandPullDown;
    @BindView(R.id.expand_headsup) Button mExpandHeadsup;
    @BindView(R.id.expand_custom) Button mExpandCustom;

    @BindString(R.string.normal_notification_title) String mNormalTitle;
    @BindString(R.string.normal_notification_content) String mNormalContent;

    @BindString(R.string.expand_notification_title) String mExpandTitle;
    @BindString(R.string.expand_notification_content) String mExpandContent;
    @BindString(R.string.expand_notification_big_title) String mExpandBigTitle;
    @BindString(R.string.expand_notification_big_summary) String mExpandBigSummary;

    @BindString(R.string.headsup_notification_title) String mHeadsupTitle;
    @BindString(R.string.headsup_notification_content) String mHeadsupContent;

    @BindString(R.string.headsup_fullintent_notification_title) String mHeadsupFITitle;
    @BindString(R.string.headsup_fullintent_notification_content) String mHeadsupFIContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setListen();
    }

    /**
     * Set listening for all buttons.
     */
    private void setListen() {
        mPullDown.setOnClickListener(this);
        mHeadsup.setOnClickListener(this);
        mCustom.setOnClickListener(this);
        mExpandPullDown.setOnClickListener(this);
        mExpandHeadsup.setOnClickListener(this);
        mExpandCustom.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pull_down:
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_lock_power_off)
                        .setContentTitle(mNormalTitle)
                        .setContentText(mNormalContent)
                        .show(NOTIFY_ID_PULL_DOWN);
                break;
            case R.id.headsup:
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_menu_week)
                        .setContentTitle(mHeadsupTitle)
                        .setContentText(mHeadsupContent)
                        .setHeadsup(true)
                        .show(NOTIFY_ID_HEADSUP);
                break;
            case R.id.custom:
                RemoteViews view = new RemoteViews(getPackageName(), R.layout.custom1);
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setContentTitle(mNormalTitle)
                        .setContentText(mNormalContent)
                        .setContent(view)
                        .show(NOTIFY_ID_CUSTOM);
                break;
            case R.id.expand_pull_down:
                String[] lines = new String[6];
                for (int i = 0; i < lines.length; i++) {
                    lines[i] = "line: " + i;
                }
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_media_pause)
                        .setContentTitle(mExpandTitle)
                        .setContentText(mExpandContent)
                        .setBigTitle(mExpandBigTitle)
                        .setBigSummary(mExpandBigSummary)
                        .setBigLines(lines)
                        .show(NOTIFY_ID_EXPAND_PULL_DOWN);
                break;
            case R.id.expand_headsup:
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_media_pause)
                        .setContentTitle(mExpandTitle)
                        .setContentText(mExpandContent)
                        .setBigTitle(mExpandBigTitle)
                        .setBigSummary(mExpandBigSummary)
                        .setBigLines(getResources().getStringArray(R.array.expand_content))
                        .setHeadsup(true)
                        .show(NOTIFY_ID_EXPAND_HEADSUP);
                break;
            case R.id.expand_custom:
                RemoteViews content = new RemoteViews(getPackageName(), R.layout.custom2);
                RemoteViews bigContent = new RemoteViews(getPackageName(), R.layout.custom3);
                new CNotification.Builder(MainActivity.this.getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setContentTitle(mExpandTitle)
                        .setContentText(mExpandContent)
                        .setBigSummary(mExpandBigSummary)
                        //.setContent(content)
                        .setCustomBigContent(bigContent)
                        .setHeadsup(true)
                        .show(NOTIFY_ID_EXPAND_CUSTOM);
                break;
            default:
                break;
        }
    }
}
