package com.pangmao.learnbase.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.service.ServiceActivity;
import com.pangmao.learnbase.util.LoggUtil;

import androidx.core.app.NotificationCompat;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //中断广播
        //abortBroadcast();
        String msg = intent.getStringExtra("data");
        LoggUtil.log("消息1:" + msg);


        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, ServiceActivity.class);
        Notification notification = getNotification(context, intent1,"一条通知", "明日复明日", R.mipmap.ic_launcher);
        /**
         * 参数一:id, 保证为每个通知所指定的id都是不同的
         * 参数二:notification 对象
          */
        notificationManager.notify(1, notification);
        //关闭通知id=1 的通知
//        notificationManager.cancel(1);

//        Toast.makeText(context, "消息1:"+msg, Toast.LENGTH_SHORT).show();
    }

    private Notification getNotification(Context context, Intent intent, String title, String content, int smailIconId) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                //指定通知标题内容
                .setContentTitle(title)
                //指定通知的正文
                .setContentText(content)
                //指定通知被创建的时间
                .setWhen(System.currentTimeMillis())
                //指定通知小图标
                .setSmallIcon(smailIconId)
                //指定通知大图标(下拉系统栏时会显示)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                //点击后自动消失
                .setAutoCancel(true)
                //响应点击消息
                .setContentIntent(pendingIntent);

        return builder.build();
    }
}
