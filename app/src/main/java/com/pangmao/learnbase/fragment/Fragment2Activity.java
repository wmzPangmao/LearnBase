package com.pangmao.learnbase.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.pangmao.learnbase.BaseActivity;
import com.pangmao.learnbase.R;

public class Fragment2Activity extends BaseActivity implements View.OnClickListener {

    public static void onStartActivity(Context context){
        Intent intent = new Intent(context, Fragment2Activity.class);
        context.startActivity(intent);
    }

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        findViewById(R.id.btn_fragment_content1).setOnClickListener(this);
        findViewById(R.id.btn_fragment_content2).setOnClickListener(this);
        findViewById(R.id.btn_fragment_content3).setOnClickListener(this);

        fragment = Content1Fragment.newInstance("hello", "wrold");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fg_fragmetn2_content, fragment)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        // getChildFragmentManager()是在fragment里面嵌套fragment的时候使用
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //第二种方式(replace),初始化界面
        transaction.replace(R.id.fg_fragmetn2_content, fragment);
        //添加到回退栈中.
        transaction.addToBackStack(null);
        //提交事务
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fragment_content1:
                fragment = getSupportFragmentManager().findFragmentById(R.id.fg_fragmetn2_content);
                if(!(fragment instanceof Content1Fragment)) {
                    fragment = Content1Fragment.newInstance("hello", "wrold");
                    replaceFragment(fragment);
                }else {
                    showToast("不需要替换");
                }
                break;
            case R.id.btn_fragment_content2:
                replaceFragment(Content2Fragment.newInstance("hello", "wrold"));
//                fragment = Content2Fragment.newInstance("hello", "wrold");
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.fg_fragmetn2_content, fragment)
//                        .addToBackStack(null)
//                        .commit();
                break;
            case R.id.btn_fragment_content3:
//                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                replaceFragment(Content3Fragment.newInstance("hello", "wrold"));
                break;
                default:
        }
    }
}
