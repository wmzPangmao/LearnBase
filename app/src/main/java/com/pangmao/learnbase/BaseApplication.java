package com.pangmao.learnbase;

import android.content.Context;

import com.pangmao.learnbase.savedata.SpStorageUtil;

import org.litepal.LitePalApplication;

/**
 * @author Administrator
 */
public class BaseApplication extends LitePalApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SpStorageUtil.init(context, "LearnSharedData");

    }

    public static Context getContext() {
        return context;
    }
}