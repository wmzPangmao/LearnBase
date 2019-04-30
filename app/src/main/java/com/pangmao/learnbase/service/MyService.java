package com.pangmao.learnbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.pangmao.learnbase.util.LoggUtil;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
        LoggUtil.log("onBind");
        //当其他组件调用bindService()方法时，此方法将会被调用
        //如果不想让这个service被绑定，在此返回null即可
        return null;
    }

    @Override
    public void onCreate() {
        LoggUtil.log("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LoggUtil.log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LoggUtil.log("onDestroy");
        super.onDestroy();
    }
}
