package com.pangmao.learnbase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.pangmao.learnbase.activity.SecondActivity;
import com.pangmao.learnbase.util.LoggUtil;

public class TestFunc {

    public static void testAlarm(Context context) {
        LoggUtil.log("Alarm 定时测试");
        /**
         * 获取AlarmManager 的对象.
         */
        AlarmManager manager = (AlarmManager) BaseApplication.getContext().getSystemService(Context.ALARM_SERVICE);
        /**
         * System.currentTimeMillis() -> 获取从1970-01-01-0点开始算起所经历的毫秒数
         * SystemClock.elapsedRealtime() -> 获取开机至今所经历的时间
         */
        long triggerAtTime = System.currentTimeMillis() + 15 * 1000;
        Intent intent = new Intent(context, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        /**
         * setExact(): 准确的定时
         * set():有稍许误差的定时(是为了让相近的几个任务一直执行,减少CPU唤醒次数,优化耗电性能)
         * 参数一 type:
         * ELAPSED_REALTIME_WAKEUP->  触发时间以开机时间算起,且会唤醒CPU
         * ELAPSED_REALTIME->           触发时间以开机时间算起, 但不会唤醒CPU
         * RTC_WAKEUP->                 触发时间以1970-01-01-0点 开始算起,且会唤醒CPU
         * RTC->                        触发时间以1970-01-01-0点, 但不会唤醒CPU
         * 参数二:任务触发的时间
         * 参数三:PendingIntent
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        }else {
            manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        }
    }
}
