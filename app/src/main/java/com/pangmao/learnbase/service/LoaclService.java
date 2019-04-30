package com.pangmao.learnbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.pangmao.learnbase.util.LoggUtil;

public class LoaclService extends Service {

    private final IBinder mBinder = new MyBinder();
    private String msg = "hello";

    /**
     * 在调用onBind方法时将MyBinder对象传到外部,
     * 由于MyBinder为内部类来, 可以获取BindService的对象,
     * 则将服务对象传递给外部,使服务和外部控件连接起来
     */
    class MyBinder extends Binder {

        LoaclService getService(){
            return LoaclService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LoggUtil.log("LoaclService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LoggUtil.log("LoaclService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggUtil.log("LoaclService onDestroy");
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void printMsg(){
        LoggUtil.log(msg);
    }
}

