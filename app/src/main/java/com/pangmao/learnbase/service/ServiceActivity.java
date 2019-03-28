package com.pangmao.learnbase.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LogUtil;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent = null;

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.btn_service_start).setOnClickListener(this);
        findViewById(R.id.btn_service_stop).setOnClickListener(this);
        findViewById(R.id.btn_service_bind).setOnClickListener(this);
        findViewById(R.id.btn_service_intent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_service_start:
                LogUtil.log("btn_service_start");
                intent = new Intent(this, MutilService.class);
                startService(intent);
                startService(intent);
                startService(intent);
                break;
            case R.id.btn_service_stop:
                LogUtil.log("btn_service_stop");
                if(intent != null) {
                    stopService(intent);
                    intent = null;
                }
                break;
            case R.id.btn_service_bind:
                LogUtil.log("btn_service_bind");
                break;
            case R.id.btn_service_intent:
                LogUtil.log("btn_service_intent");
                MyIntentService.startActionFoo(this, "kkk", "ll");
                break;
            default:
        }
    }
}
