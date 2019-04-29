package com.pangmao.learnbase.broadcast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.service.ServiceActivity;
import com.pangmao.learnbase.util.LoggUtil;

public class BroadcastActivity extends BaseActivity implements View.OnClickListener {

    private Intent intent = null;
    private final String ACTION = "com.pangmao.learnBase.Broadcast.receiver1";
    private String sendMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        findViewById(R.id.btn_broadcast_register).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_normal).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_order).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_cancel).setOnClickListener(this);

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
                break;
            case R.id.btn_broadcast_cancel:
                LoggUtil.log("广播手动注销");
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
                default:
        }
    }
}
