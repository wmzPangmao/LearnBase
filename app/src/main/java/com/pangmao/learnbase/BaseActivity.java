package com.pangmao.learnbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pangmao.learnbase.util.ActivityCollector;
import com.pangmao.learnbase.util.LoggUtil;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void showToast(String msg){
        LoggUtil.log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
