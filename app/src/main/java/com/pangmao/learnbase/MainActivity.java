package com.pangmao.learnbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pangmao.learnbase.activity.IntentActivity;
import com.pangmao.learnbase.service.ServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_activity).setOnClickListener(this);
        findViewById(R.id.btn_main_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_main_service).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_activity:
                IntentActivity.onStartActivity(this);
                break;
            case R.id.btn_main_broadcast:

                break;
            case R.id.btn_main_service:
                ServiceActivity.onStartActivity(this);
                break;
                default:
        }
    }
}
