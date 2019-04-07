package com.pangmao.learnbase.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.util.LogUtil;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.btn_service_start).setOnClickListener(this);
        findViewById(R.id.btn_service_stop).setOnClickListener(this);
        findViewById(R.id.btn_service_intent).setOnClickListener(this);
        findViewById(R.id.btn_service_bind_1).setOnClickListener(this);
        findViewById(R.id.btn_service_bind_2).setOnClickListener(this);
        findViewById(R.id.btn_service_bind_3).setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null) {
            stopService(intent);
            intent = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_service_start:
                LogUtil.log(TAG,"btn_service_start");
                intent = new Intent(this, MutilService.class);
                startService(intent);
                startService(intent);
                startService(intent);
                break;
            case R.id.btn_service_stop:
                LogUtil.log(TAG,"btn_service_stop");
                if(intent != null) {
                    stopService(intent);
                    intent = null;
                }
                break;
            case R.id.btn_service_intent:
                LogUtil.log(TAG, "btn_service_intent");
                MyIntentService.startActionFoo(this, "kkk", "ll");
                break;
            case R.id.btn_service_bind_1:
                LogUtil.log(TAG,"btn_service_bind_1");
                LocalActivity.onStartActivity(this);
                break;
            case R.id.btn_service_bind_2:
                LogUtil.log(TAG,"btn_service_bind_2");
                MessengerActivity.onStartActivity(this);
                break;
            case R.id.btn_service_bind_3:
                LogUtil.log(TAG,"btn_service_bind_3");
                break;
            default:
        }
    }


    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }
}
