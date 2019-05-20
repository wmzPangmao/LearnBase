package com.pangmao.learnbase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.pangmao.learnbase.activity.IntentActivity;
import com.pangmao.learnbase.broadcast.BroadcastActivity;
import com.pangmao.learnbase.fragment.Fragment2Activity;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.savedata.DataActivity;
import com.pangmao.learnbase.service.ServiceActivity;
import com.pangmao.learnbase.util.LoggUtil;
import com.pangmao.learnbase.util.PermissionUtil;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author wangmingzhi
 */
public class MainActivity extends BaseActivity implements
        EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private Context context;
    @SuppressLint("InlinedApi")
    private String[] mPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    public static final int CODE = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        //检查权限，防止重复获取
        mPermissions = PermissionUtil.getDeniedPermissions(this, mPermissions);
        if (mPermissions != null && mPermissions.length > 0) {
            /**
             * 1.上下文
             * 2.权限失败后弹出对话框的内容
             * 3.requestCode
             * 4.要申请的权限
             */
            EasyPermissions.requestPermissions(this, PermissionUtil.permissionText(mPermissions), CODE, mPermissions);
        } else {
            LoggUtil.log("不需要动态获取权限");
        }
    }

    @OnClick({R.id.btn_main_activity, R.id.btn_main_broadcast, R.id.btn_main_service, R.id.btn_main_handler, R.id.btn_main_fragment, R.id.btn_main_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main_activity:
                IntentActivity.onStartActivity(context);
                break;
            case R.id.btn_main_broadcast:
                BroadcastActivity.onStartActivity(context);
                break;
            case R.id.btn_main_service:
                ServiceActivity.onStartActivity(context);
                break;
            case R.id.btn_main_handler:
                HandlerActivity.onStartActivity(context);
                break;
            case R.id.btn_main_fragment:
                Fragment2Activity.onStartActivity(context);
                break;
            case R.id.btn_main_data:
                DataActivity.onStartActivity(context);
                break;
                default:
        }
    }


    //所有权限申请成功
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        LoggUtil.log("ok");
    }

    //权限获取失败的回调
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //存在被永久拒绝(拒绝&不再询问)的权限
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            mPermissions = PermissionUtil.getDeniedPermissions(this, mPermissions);
            PermissionUtil.PermissionDialog(this, PermissionUtil.permissionText(mPermissions) + "请在应用权限管理进行设置！");
        } else {
            EasyPermissions.requestPermissions(this, PermissionUtil.permissionText(mPermissions), CODE, mPermissions);
        }
    }

    //权限被拒绝后的显示提示对话框，点击确认的回调
    @Override
    public void onRationaleAccepted(int requestCode) {
        LoggUtil.log("lll");
    }

    //权限被拒绝后的显示提示对话框，点击取消的回调
    @Override
    public void onRationaleDenied(int requestCode) {
        LoggUtil.log("kkkk");
    }

    public void testTrans() {
        TestFunc.testAlarm(context);
    }
}
