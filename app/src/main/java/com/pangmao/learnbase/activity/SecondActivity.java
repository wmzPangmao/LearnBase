package com.pangmao.learnbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LoggUtil;

/**
 * @author wangmingzhi
 */
public class SecondActivity extends BaseActivity {

    public final static String ACTION = "android.intent.action.SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvAction = findViewById(R.id.tv_second_action);
        TextView tvCategory = findViewById(R.id.tv_second_category);

        Intent intent = getIntent();
        if (intent != null){
            tvAction.setText("Action:" + intent.getAction());
            tvCategory.setText("Category:" + intent.getCategories());
            LoggUtil.log(intent.getDataString());
        }else {
            tvAction.setText("Action:" + "空");
            tvCategory.setText("Category:" + "空");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "SecondActivity" + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "SecondActivity" + "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "SecondActivity" + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "SecondActivity" + "onDestroy");
    }
}
