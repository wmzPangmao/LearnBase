package com.pangmao.learnbase;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.pangmao.learnbase.activity.IntentActivity;
import com.pangmao.learnbase.broadcast.BroadcastActivity;
import com.pangmao.learnbase.fragment.Fragment2Activity;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.service.ServiceActivity;

/**
 * @author wangmingzhi
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_activity).setOnClickListener(this);
        findViewById(R.id.btn_main_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_main_service).setOnClickListener(this);
        findViewById(R.id.btn_main_handler).setOnClickListener(this);
        findViewById(R.id.btn_main_fragment).setOnClickListener(this);

        context = this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_activity:
                IntentActivity.onStartActivity(context);
                break;
            case R.id.btn_main_broadcast:
                BroadcastActivity.onStartActivity(context);
                break;
            case R.id.btn_main_service:
                ServiceActivity.onStartActivity(context);
                break;
            case R.id.btn_main_handler:
                HandlerActivity.onStartActivity(context);
                break;
            case R.id.btn_main_fragment:
                Fragment2Activity.onStartActivity(context);
                break;
                default:
        }
    }
}
