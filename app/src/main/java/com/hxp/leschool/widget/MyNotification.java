package com.hxp.leschool.widget;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.MainActivity;

/**
 * 通知widget
 * Created by hxp on 17-2-4.
 */

public class MyNotification {

    private Context context = MyApplication.getInstance();
    private String title;//通知标题
    private String content;//通知内容
    private int icon;//通知图标
    private int id;//通知id

    public MyNotification(String title, int icon, String content, int id) {
        this.title = title;
        this.icon = icon;
        this.content = content;
        this.id = id;

        initView();
    }

    //显示通知
    private void initView() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(icon).setContentTitle(title).setContentText(content);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_ALL;
        notification.flags=Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(id, notification);
    }
}
