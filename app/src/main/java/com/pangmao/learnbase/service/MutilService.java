package com.pangmao.learnbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.pangmao.learnbase.util.LogUtil;

/**
 * 同时处理多个请求
 */
public class MutilService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            LogUtil.log("处理业务开始:" + msg.arg1);
            //为每个请求创建线程,保证同时处理
            new MyThread(msg.arg1).start();
        }

        private class MyThread extends Thread{
            int transId;

            MyThread(int transId) {
                this.transId = transId;
            }

            @Override
            public void run() {
                super.run();
                final long endTime = System.currentTimeMillis() + 5 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                LogUtil.log("处理业务结束:" + transId);
                stopSelf(transId);
            }
        }
    }

    @Override
    public void onCreate() {

        HandlerThread thread = new HandlerThread("ServiceStartArguments",3);
//                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.log("service starting " + startId);
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        LogUtil.log("service done");
        if(mServiceLooper != null) {
            mServiceLooper.quit();
        }
    }
}
