package com.pangmao.learnbase.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.MainActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LogUtil;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author wangmingzhi
 */
public class HandlerActivity extends BaseActivity implements View.OnClickListener {

    private MyHandler handler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        findViewById(R.id.btn_handler_send).setOnClickListener(this);

        // 将当前线程初始化为Looper线程
        MyLooper.prepare();
        // 实例化handler
        handler1 = new MyHandler(){
            @Override
            public void handleMessage(MyMessage msg) {
                LogUtil.log("Thread" + Thread.currentThread() + "--recv1: " + msg.toString());
            }
        };

    }


    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, HandlerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_handler_send:
                new LooperThread().start();
                showToast("handler消息测试");
                MyLooper.loop();
                break;
            case R.id.btn_handler_async:
                AsyncTaskDemo asy = new AsyncTaskDemo();
                asy.execute(HandlerActivity.this);
                break;
                default:
        }
    }


    class LooperThread extends Thread {

        private MyHandler handler2;

        @Override
        public void run() {
            // 将当前线程初始化为Looper线程
            MyLooper.prepare();
            // 实例化handler
            handler2 = new MyHandler(){
                @Override
                public void handleMessage(MyMessage msg) {
                    LogUtil.log("Thread" + Thread.currentThread() + "--recv2: " + msg.toString());
                }
            };

            for (int i = 0; i < 10; i++){
                MyMessage msg = new MyMessage();
                msg.obj = UUID.randomUUID().toString();
                LogUtil.log("Thread" + Thread.currentThread() + "--send1: " + msg.obj.toString());
                //发送消息
                handler1.sendMessage(msg);
            }
            for (int j = 0; j < 10; j++){
                MyMessage msg = new MyMessage();
                msg.obj = UUID.randomUUID().toString();
                LogUtil.log("Thread" + Thread.currentThread() + "--send2: " + msg.obj.toString());
                //发送消息
                handler2.sendMessage(msg);
            }
            // 开始循环处理消息队列
            MyLooper.loop();
            LogUtil.log("end");
        }
    }
}
