package com.pangmao.learnbase.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyLocalReceiver extends BroadcastReceiver {
    public MyLocalReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String msg = intent.getStringExtra("data");
        Toast.makeText(context, "消息4:"+msg, Toast.LENGTH_SHORT).show();
    }
}
