package com.pangmao.learnbase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.View;

import com.pangmao.learnbase.activity.IntentActivity;
import com.pangmao.learnbase.broadcast.BroadcastActivity;
import com.pangmao.learnbase.fragment.Fragment2Activity;
import com.pangmao.learnbase.handler.HandlerActivity;
import com.pangmao.learnbase.service.ServiceActivity;
import com.pangmao.learnbase.util.LoggUtil;
import com.pangmao.learnbase.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author wangmingzhi
 */
public class MainActivity extends BaseActivity implements View.OnClickListener,
        EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private Context context;
    @SuppressLint("InlinedApi")
    private String[] mPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,
                Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    public static final int CODE = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_activity).setOnClickListener(this);
        findViewById(R.id.btn_main_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_main_service).setOnClickListener(this);
        findViewById(R.id.btn_main_handler).setOnClickListener(this);
        findViewById(R.id.btn_main_fragment).setOnClickListener(this);
        findViewById(R.id.btn_main_content_provider).setOnClickListener(this);

        context = this;

//        BaseApplication app = (BaseApplication) getApplication();
//        String appStr = app.getAppStr();
//        LoggUtil.log(appStr);
//        appStr = "123";
//        app.setAppStr(appStr);

        //检查权限，防止重复获取
        mPermissions = PermissionUtil.getDeniedPermissions(this, mPermissions);
        if (mPermissions!= null && mPermissions.length > 0) {
            /**
             * 1.上下文
             * 2.权限失败后弹出对话框的内容
             * 3.requestCode
             * 4.要申请的权限
             */
            EasyPermissions.requestPermissions(this, PermissionUtil.permissionText(mPermissions), CODE, mPermissions);
        }else {
            LoggUtil.log("不需要动态获取权限");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
            case R.id.btn_main_content_provider:
                getReadContentData();
                break;
                default:
        }
    }

    private List<String> getReadContentData() {
        ContentValues values;
        Cursor cursor = null;
        //通讯录提供的共享数据的地址
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        List<String> contactsList = new ArrayList<>();

        try {
            cursor = getContentResolver().query(uri, null, null, null, null);
            if(cursor == null) {
                LoggUtil.log("通讯录无数据!");
            }else {
                while (cursor.moveToNext()) {
                    // 获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    // 获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //获取联系人邮箱
                    String email = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.SEND_TO_VOICEMAIL));
                    contactsList.add(displayName + "\n" + number + "\n" + email);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor !=null){
                cursor.close();
            }
            for (int i = 0; i< contactsList.size(); i++){
                LoggUtil.log(contactsList.get(i));
            }
        }
        return contactsList;
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
}
