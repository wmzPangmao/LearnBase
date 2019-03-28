package com.pangmao.learnbase.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import com.pangmao.learnbase.util.LogUtil;

public class MessengerService extends Service {
    public static final int MSG_SAY_HELLO = 1;

    @SuppressLint("HandlerLeak")
    class ServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    //当收到客户端的message时，显示hello
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger mMessenger = new Messenger(new ServiceHandler());

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.log("MessengerService binding");
        //返回给客户端一个IBinder实例
        return mMessenger.getBinder();
    }
}
