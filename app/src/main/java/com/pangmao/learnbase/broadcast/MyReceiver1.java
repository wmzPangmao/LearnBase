package com.pangmao.learnbase.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.pangmao.learnbase.util.LoggUtil;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //中断广播
        abortBroadcast();
        String msg = intent.getStringExtra("data");
        LoggUtil.log("消息1:" + msg);
//        Toast.makeText(context, "消息1:"+msg, Toast.LENGTH_SHORT).show();
    }
}
