package com.pangmao.learnbase.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LogUtil;

public class MessengerActivity extends AppCompatActivity implements View.OnClickListener  {

    private Messenger mService = null;
    private boolean mBound = false;
    private Intent intent = null;

    private ServiceConnection mConnection = new ServiceConnection() {

        /**
         * 接连成功时,系统会调用该方法以传递服务的　onBind() 方法返回的 IBinder。
         */
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            //获取绑定的服务
            mService = new Messenger(service);
            mBound = true;
            LogUtil.log("绑定成功");
        }
        /**
         * Android系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。
         * 当客户端取消绑定时，系统“绝对不会”调用该方法。
         */
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            mService = null;
            LogUtil.log("绑定意外解除!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        findViewById(R.id.btn_service_bind2).setOnClickListener(this);
        findViewById(R.id.btn_service_unbind2).setOnClickListener(this);
        findViewById(R.id.btn_service_test2).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBound) {
            unbindService(mConnection);
            mBound = false;
            LogUtil.log("解除绑定成功");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service_bind2:
                LogUtil.log("btn_service_bind");
                if (!mBound) {
                    intent = new Intent(this, MessengerService.class);
                    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                }
                break;
            case R.id.btn_service_unbind2:
                LogUtil.log("btn_service_unbind");
                if (mBound) {
                    unbindService(mConnection);
                    mBound = false;
                    LogUtil.log("解除绑定成功");
                }
                break;
            case R.id.btn_service_test2:
                LogUtil.log("btn_service_test");
                if (mBound) {
                    sayHello();
                }
                break;
            default:
        }
    }
    //调用此方法时会发送信息给服务端
    public void sayHello() {
        if (!mBound) {
            return;
        }
        //发送一条信息给服务端
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, MessengerActivity.class);
        context.startActivity(intent);
    }
}
