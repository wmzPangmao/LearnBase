package com.pangmao.learnbase.broadcast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.service.ServiceActivity;

public class BroadcastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }


    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, BroadcastActivity.class);
        context.startActivity(intent);
    }
}
