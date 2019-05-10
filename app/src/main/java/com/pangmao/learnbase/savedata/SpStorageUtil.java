package com.pangmao.learnbase.savedata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.pangmao.learnbase.util.LoggUtil;

/**
 *
 * @author wangmz-pc
 * @date 2017/12/11 0011
 */
public class SpStorageUtil {
    private static SharedPreferences sp = null;

    public static void init(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static String read(String key) {
        return sp.getString(key, "");
    }

    public static String read(String key, String defalut) {
        return sp.getString(key, defalut);
    }

    public static void save(String key, String value) {
        SharedPreferences.Editor edit = sp.edit();
        if(edit != null) {
            edit.putString(key, value).apply();
            LoggUtil.log("key:" + key + " value:" + value);
        }
    }

    public static void remove(String key){
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
    }

    public static void clear() {
        SharedPreferences.Editor edit = sp.edit();
        edit.clear().apply();
    }
}
