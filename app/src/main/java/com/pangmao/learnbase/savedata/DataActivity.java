package com.pangmao.learnbase.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LoggUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class DataActivity extends BaseActivity implements View.OnClickListener {

    public static void onStartActivity(Context context){
        try {
            String clazzName = new Object() {
                String getClassName() {
                    String clazzName = this.getClass().getName();
                    return clazzName.substring(0, clazzName.lastIndexOf('$'));
                }
            }.getClassName();
            LoggUtil.log(clazzName);
            Class clazz = Class.forName(clazzName);
            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Context mContext;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mContext = this;
        //android文件常用存储路径
        /**
         * 内部存储
         * "/data/data/packagename/cache"
         */
        path = mContext.getCacheDir().getAbsolutePath();
        /**
         * 内部存储
         * "/data/data/packagename/files"
         */
        path = mContext.getFilesDir().getAbsolutePath();
        /**
         * 外部存储
         * "/storage/emulated/0/Android/data/packagename/cache"
         */
//        path = mContext.getExternalCacheDir().getAbsolutePath();
        /**
         * 外部存储
         * "/storage/emulated/0"
         */
//        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        /**
         * 外部存储
         * "/storage/emulated/0/DCIM"
         */
//        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        /**
         * 外部存储
         * "/storage/emulated/0/Android/data/packagename/files/Pictures"
         */
//        path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        findViewById(R.id.btn_data_file_read).setOnClickListener(this);
        findViewById(R.id.btn_data_file_save).setOnClickListener(this);
        findViewById(R.id.btn_data_shared_read).setOnClickListener(this);
        findViewById(R.id.btn_data_shared_save).setOnClickListener(this);
        findViewById(R.id.btn_data_litepal_del).setOnClickListener(this);
        findViewById(R.id.btn_data_litepal_add).setOnClickListener(this);
        findViewById(R.id.btn_data_litepal_update).setOnClickListener(this);
        findViewById(R.id.btn_data_litepal_query).setOnClickListener(this);
        findViewById(R.id.btn_data_litepal_deleteall).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String value = "";
        Student student;
        int number;
        switch (v.getId()){
            case R.id.btn_data_file_read:
                try {
                    value = FileUtil.read(path + "/test.txt");
                    showToast(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_data_file_save:
                FileUtil.deal(path + "/test.txt");
                if(FileUtil.write("12345678", path + "/test.txt")) {
                    showToast("文件保存成功");
                }else {
                    showToast("文件保存失败");
                }
                break;
            case R.id.btn_data_shared_read:
                value = SpStorageUtil.read("SchoolName", "");
                showToast(value);
                break;
            case R.id.btn_data_shared_save:
                SpStorageUtil.save("SchoolName", "希望小学");
                break;
            case R.id.btn_data_litepal_add:
                student = new Student("小明", "1105110127");
                student.setId(1);
                student.save();
                break;
            case R.id.btn_data_litepal_del:
                number = LitepalUtil.deleteAll(Student.class, "name = ? and schoolNo = ?",
                        "小明", "1105110127");
                showToast("" + number);
                break;
            case R.id.btn_data_litepal_update:
                ContentValues values = new ContentValues();
                values.put("schoolNo", "1105110145");
                number = LitepalUtil.updateAll(Student.class, values, "name = ?", "小明");
                showToast("" + number);
                break;
            case R.id.btn_data_litepal_query:
                List list = LitepalUtil.queryAll(Student.class, null, null, null, 0, 0);
                if(list.size() > 0) {
                    student = (Student) list.get(0);
                    showToast(student.toString());
                }else {
                    showToast("数据库中没有数据");
                }
                break;
            case R.id.btn_data_litepal_deleteall:
                number = LitePal.deleteAll(Student.class);
                showToast("" + number);
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


}
