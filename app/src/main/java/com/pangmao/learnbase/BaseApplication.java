package com.pangmao.learnbase;

import com.pangmao.learnbase.savedata.SpStorageUtil;

import org.litepal.LitePalApplication;

/**
 * @author Administrator
 */
public class BaseApplication extends LitePalApplication {

    private String appStr = "";

    @Override
    public void onCreate() {
        super.onCreate();

        appStr = "init Learn";
        SpStorageUtil.init(getContext(), "LearnSharedData");
    }

    public String getAppStr() {
        return appStr;
    }

    public void setAppStr(String appStr) {
        this.appStr = appStr;
    }
}