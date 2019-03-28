package com.pangmao.learnbase.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pangmao.learnbase.R;

/**
 * @author wangmingzhi
 */
public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Intent intent;

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, IntentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        context = this;
        Button btn = findViewById(R.id.btn_intent_common);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btn_intent_common1);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btn_intent_common2);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btn_intent_action);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btn_intent_action2);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btn_intent_action3);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_intent_common:
                intent = new Intent(this, SecondActivity.class);
                break;
            case R.id.btn_intent_common1:
                intent = new Intent();
                intent.setClassName("com.pangmao.learnbase", "com.pangmao.learnbase.activity.SecondActivity");
                break;
            case R.id.btn_intent_common2:
                intent = new Intent();
                intent.setComponent(new ComponentName("com.pangmao.learnbase", "com.pangmao.learnbase.activity.SecondActivity"));
                break;
            case R.id.btn_intent_action:
                intent = new Intent();
                intent.setAction(SecondActivity.ACTION);
                break;
            case R.id.btn_intent_action2:
                intent = new Intent();
                intent.setAction(SecondActivity.ACTION);
                intent.addCategory("android.intent.category.MYDEF");
                break;
            case R.id.btn_intent_action3:
                intent = new Intent();
                intent.setAction(SecondActivity.ACTION);
                intent.addCategory("android.intent.category.MYDEF222");
                break;
                default:
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else {
            Log.d("TAG", "Action:" + intent.getAction() + "\tCategory" + intent.getCategories() + "匹配不存在!");
        }
    }
}
