package com.pangmao.learnbase.util;

import android.util.Log;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class LogUtil {

    public static void log(String msg){
        Log.e("TAG", DateUtil.date(new Date()).toString("hh:mm:ss") + "->" +msg);
    }
}
