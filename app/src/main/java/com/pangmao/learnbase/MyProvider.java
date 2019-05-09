package com.pangmao.learnbase;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by wangmz-pc on 2016/11/16 0016.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("", "table1", TABLE1_DIR);
        uriMatcher.addURI("", "table1/#", TABLE1_ITEM);
    }

    @Override
    //初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作
    public boolean onCreate() {
        return false;
    }



    @Nullable
    @Override
    //根据传入的内容 URI来返回相应的 MIME类型。
    public String getType(Uri uri) {
        String value = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                value = "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
                break;
            case TABLE1_ITEM:
                value = "vnd.android.cursor.item/vnd.com.example.app.provider.table1/1";
                break;
            default:
        }
        return value;
    }

    @Nullable
    @Override
    /*
    从内容提供器中查询数据。使用 uri 参数来确定查询哪张表，projection参数用于确
    定查询哪些列，selection和 selectionArgs参数用于约束查询哪些行，sortOrder 参数用于
    对结果进行排序，查询的结果存放在 Cursor 对象中返回。
     */
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                //处理table1 表中所有数据
                break;
            case TABLE1_ITEM:
                //处理table1 表中的单条数据
                break;
            default:
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                //处理table1 表中所有数据
                break;
            case TABLE1_ITEM:
                //处理table1 表中的单条数据
                break;
            default:
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                //处理table1 表中所有数据
                break;
            case TABLE1_ITEM:
                //处理table1 表中的单条数据
                break;
            default:
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                //处理table1 表中所有数据
                break;
            case TABLE1_ITEM:
                //处理table1 表中的单条数据
                break;
            default:
        }
        return 0;
    }
}
