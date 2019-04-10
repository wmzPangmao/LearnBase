package com.pangmao.learnbase;

import android.app.Application;

/**
 * @author Administrator
 */
public class BaseApplication extends Application {

    private String appStr = "";

    @Override
    public void onCreate() {
        super.onCreate();

        appStr = "init Learn";
    }

    public String getAppStr() {
        return appStr;
    }

    public void setAppStr(String appStr) {
        this.appStr = appStr;
    }
}