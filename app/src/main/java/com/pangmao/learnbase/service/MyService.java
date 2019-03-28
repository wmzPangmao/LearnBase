package com.pangmao.learnbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.pangmao.learnbase.util.LogUtil;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
        LogUtil.log("onBind");
        //当其他组件调用bindService()方法时，此方法将会被调用
        //如果不想让这个service被绑定，在此返回null即可
        return null;
    }

    @Override
    public void onCreate() {
        LogUtil.log("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.log("onDestroy");
        super.onDestroy();
    }
}
