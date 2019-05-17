package com.pangmao.learnbase.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LoggUtil;

public class BroadcastActivity extends BaseActivity implements View.OnClickListener {

    private Intent intent = null;
    private final String ACTION = "com.pangmao.learnBase.Broadcast.receiver1";
    private String sendMsg = "";
    private MyReceiver3 myReceiver3;
    private LocalBroadcastManager localBroadcastManager;
    private MyLocalReceiver myLocalReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        findViewById(R.id.btn_broadcast_register).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_normal).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_order).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_cancel).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_local).setOnClickListener(this);

        localBroadcastManager = LocalBroadcastManager.getInstance(BroadcastActivity.this);
    }


    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, BroadcastActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_broadcast_register:
                LoggUtil.log("广播手动注册");
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ACTION); //响应哪些消息
                intentFilter.setPriority(11);    //设定优先级
                if(myReceiver3 == null){
                    myReceiver3 = new MyReceiver3();
                }
                registerReceiver(myReceiver3, intentFilter); //手动注册
                if(myLocalReceiver == null) {
                    myLocalReceiver = new MyLocalReceiver();
                }
                localBroadcastManager.registerReceiver(myLocalReceiver, intentFilter);  //注册本地广播
                break;
            case R.id.btn_broadcast_cancel:
                LoggUtil.log("广播手动注销");
                if (myReceiver3 != null){
                    unregisterReceiver(myReceiver3);
                    myReceiver3 = null;
                    LoggUtil.log("myReceiver3 注销");
                }
                if(myLocalReceiver != null) {
                    localBroadcastManager.unregisterReceiver(myLocalReceiver); //注销本地广播
                    myLocalReceiver = null;
                    LoggUtil.log("myLocalReceiver 注销");
                }
                break;
            case R.id.btn_broadcast_normal:
                LoggUtil.log("发送普通广播");
                sendMsg= "normal";
                intent = new Intent(ACTION);
                intent.putExtra("data", sendMsg);
                sendBroadcast(intent);
                break;
            case R.id.btn_broadcast_order:
                LoggUtil.log("发送有序广播");
                sendMsg = "order";
                intent = new Intent(ACTION);
                intent.putExtra("data", sendMsg);
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.btn_broadcast_local:
                LoggUtil.log("发送本地广播");
                sendMsg = "local";
                intent = new Intent(ACTION);
                intent.putExtra("data", sendMsg);
                localBroadcastManager.sendBroadcast(intent);
                break;
                default:
        }
    }
}
