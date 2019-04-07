package com.pangmao.learnbase.util;

import android.util.Log;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

/**
 * @author wangmingzhi
 */
public class LogUtil {

    public static void log(String tag, String msg){
        Log.e(tag, DateUtil.date(new Date()).toString("hh:mm:ss") + "->" +msg);
    }

    public static void log(String msg){
        Log.e("TAG", DateUtil.date(new Date()).toString("hh:mm:ss") + "->" +msg);
    }
}
