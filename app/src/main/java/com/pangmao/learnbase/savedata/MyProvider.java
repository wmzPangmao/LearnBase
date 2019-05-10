package com.pangmao.learnbase.savedata;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.litepal.LitePal;


/**
 * @author Administrator
 */
public class MyProvider extends ContentProvider {

    private static final int TABLE1_DIR = 0;
    private static final int TABLE1_ITEM = 1;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("", "student", TABLE1_DIR);
        uriMatcher.addURI("", "student/#", TABLE1_ITEM);
    }

    @Override
    //初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作
    public boolean onCreate() {
        return true;
    }


    @Nullable
    @Override
    //根据传入的内容 URI来返回相应的 MIME类型。
    public String getType(@NonNull Uri uri) {
        String value = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                value = "vnd.android.cursor.dir/vnd.com.pangmao.learnbase.provider.student";
                break;
            case TABLE1_ITEM:
                value = "vnd.android.cursor.item/vnd.com.pangmao.learnbase.provider.student";
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
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                //处理table1 表中所有数据
                cursor = LitePal.findBySQL("select * from Student");
                break;
            case TABLE1_ITEM:
                //处理table1 表中的单条数据
                String id = uri.getPathSegments().get(1);
                cursor = LitePal.findBySQL("select * from Student where id = " + id);
                break;
            default:
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
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
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
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
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
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
