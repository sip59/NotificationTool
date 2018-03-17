package com.zozo.notificationtool;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Colyn.Lu on 2018/3/16 0016.
 *
 * 由于参数组合较多，这里采用建造者模式
 */

public class CNotification {
    private static final String TAG = "CNotification";
    private static NotificationManager mNM;

    public static class Builder {
        private Context mContext;
        NotificationCompat.Builder mBuilder;
        NotificationCompat.InboxStyle mStyle; // For expand notification.
        Builder(Context context) {
            mContext = context;
            mBuilder = new NotificationCompat.Builder(context);
            if (mNM == null) {
                Log.d(TAG, "mNM == null");
                mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            }
        }

        /**
         * Set a small icon for the notification.
         * @param resId
         * @return
         */
        public Builder setSmallIcon(int resId) {
            mBuilder.setSmallIcon(resId);
            return this;
        }

        /**
         * Set a title id for the notification.
         * @param resId
         * @return
         */
        public Builder setContentTitle(int resId) {
            mBuilder.setContentTitle(mContext.getString(resId));
            return this;
        }

        /**
         * Set a title string for the notification.
         * @param title
         * @return
         */
        public Builder setContentTitle(String title) {
            mBuilder.setContentTitle(title);
            return this;
        }

        /**
         * Set a content text id for the notification.
         * @param resId
         * @return
         */
        public Builder setContentText(int resId) {
            mBuilder.setContentText(mContext.getString(resId));
            return this;
        }

        /**
         * Set a content text string for the notification.
         * @param content
         * @return
         */
        public Builder setContentText(String content) {
            mBuilder.setContentText(content);
            return this;
        }

        /**
         * Set a bool value to control the notification headsup.
         * @param bool
         * @return
         */
        public Builder setHeadsup(boolean bool) {
            if (bool) {
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setVibrate(new long[] {0,300,0,300});
            }
            return this;
        }

        /**
         * Set a pendingIntent for the notification that it will heads up.
         * @param intent
         * @return
         */
        public Builder setFullScreenIntent(PendingIntent intent) {
            mBuilder.setFullScreenIntent(intent, true);
            return this;
        }

        /**
         * Set a title for the expanded notification.
         * @param title
         * @return
         */
        public Builder setBigTitle(String title) {
            if (mStyle == null) mStyle = new NotificationCompat.InboxStyle();

            mStyle.setBigContentTitle(title);
            mBuilder.setStyle(mStyle);
            return this;
        }

        /**
         * Set the content for the expanded notification.
         * @param lines
         * @return
         */
        public Builder setBigLines(String[] lines) {
            if (mStyle == null) mStyle = new NotificationCompat.InboxStyle();

            for (String line : lines) {
                mStyle.addLine(line);
            }
            mBuilder.setStyle(mStyle);
            return this;
        }

        /**
         * Set a summary for the expandable notification.
         * @param summary
         * @return
         */
        public Builder setBigSummary(String summary) {
            if (mStyle == null) mStyle = new NotificationCompat.InboxStyle();

            mStyle.setSummaryText(summary);
            mBuilder.setStyle(mStyle);
            return this;
        }

        public Builder setContent(RemoteViews content) {
            mBuilder.setCustomContentView(content);
            //mBuilder.setContent(content);
            return this;
        }

        public Builder setCustomBigContent(RemoteViews content) {
            mBuilder.setCustomBigContentView(content);
            return this;
        }

        /**
         * Show the notification with a notification id.
         * @param notifyID
         */
        public void show(int notifyID) {
            mNM.notify(notifyID, mBuilder.build());
        }
    }
}
