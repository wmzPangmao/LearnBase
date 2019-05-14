package com.pangmao.learnbase.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.FileUtil;

import java.io.File;

/**
 * @author wangmingzhi
 */
public class IntentActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private Intent intent;

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
                intent.setDataAndType(Uri.fromFile(new File(FileUtil.path + "111.png")),
                        "image/png");
                break;
            case R.id.btn_intent_action2:
                intent = new Intent();
                intent.setAction(SecondActivity.ACTION);
                intent.addCategory("android.intent.category.MYDEF");
                intent.setData(Uri.parse("content://com.pangmao.learnbase.provider/student"));
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
            Log.d("TAG", "Action:" + intent.getAction() + "匹配不存在!");
        }
    }

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, IntentActivity.class);
        context.startActivity(intent);
    }
}
