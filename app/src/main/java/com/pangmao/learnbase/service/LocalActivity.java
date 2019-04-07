package com.pangmao.learnbase.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LogUtil;

public class LocalActivity extends BaseActivity implements View.OnClickListener {

    private LoaclService mService = null;
    private boolean mBound = false;
    private Intent intent = null;

    private ServiceConnection mConnection = new ServiceConnection() {

        /**
         * 接连成功时,系统会调用该方法以传递服务的　onBind() 方法返回的 IBinder。
         */
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LoaclService instance
            LoaclService.MyBinder myBinder = (LoaclService.MyBinder) service;
            //获取绑定的服务
            mService = myBinder.getService();
            mBound = true;
            LogUtil.log(TAG,"绑定成功");
        }
        /**
         * Android系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。
         * 当客户端取消绑定时，系统“绝对不会”调用该方法。
         */
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            mService = null;
            LogUtil.log(TAG,"绑定意外解除!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        findViewById(R.id.btn_service_bind).setOnClickListener(this);
        findViewById(R.id.btn_service_unbind).setOnClickListener(this);
        findViewById(R.id.btn_service_test).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBound) {
            unbindService(mConnection);
            mBound = false;
            LogUtil.log(TAG,"解除绑定成功!");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service_bind:
                LogUtil.log(TAG,"btn_service_bind");
                if (!mBound) {
                    intent = new Intent(this, LoaclService.class);
                    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                }
                break;
            case R.id.btn_service_unbind:
                LogUtil.log(TAG,"btn_service_unbind");
                if (mBound) {
                    unbindService(mConnection);
                    mBound = false;
                    LogUtil.log("解除绑定成功");
                }
                break;
            case R.id.btn_service_test:
                LogUtil.log(TAG,"btn_service_test");
                if (mBound) {
                    mService.printMsg();
                    mService.setMsg("hello world...");
                }
                break;
            default:
        }
    }

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, LocalActivity.class);
        context.startActivity(intent);
    }
}
