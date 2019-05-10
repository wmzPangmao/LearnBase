package com.pangmao.learnbase.savedata;

import android.content.ContentValues;

import org.litepal.FluentQuery;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class LitepalUtil {

    public static int deleteObject(Object obj){
        if(obj instanceof LitePalSupport) {
            LitePalSupport litePalData = (LitePalSupport) obj;
            if(litePalData.isSaved()) {
                litePalData.delete();
            }
        }
        return 0;
    }

    /**
     * LitePal.deleteAll(Student.class, "name = ? and schoolNo = ?",
     *      "小明", "1105110127");
     * @return 删除的数量
     */
    public static int deleteAll(Class<?> modelClass, String... conditions) {
        return LitePal.deleteAll(modelClass, conditions);
    }

    /**
     * ContentValues values = new ContentValues();
     * values.put("title", "今日iPhone6 Plus发布");
     * DataSupport.updateAll(News.class, values, "title = ? and commentcount > ?", "今日iPhone6发布", "0");
     * @return 修改的数量
     */
    public static int updateAll(Class<?> modelClass, ContentValues values, String... conditions) {
        return LitePal.updateAll(modelClass, values, conditions);
    }

    /**
     * List<News> newsList = DataSupport.select("title", "content")
     *                                  .where("commentcount > ?", "0")
     *                                  .order("publishdate desc")
     *                                  .offset(10)
     *                                  .limit(10)
     *                                  .find(News.class);
     * @return 返回查询的数据
     */
    public static List queryAll(Class clazz, String[] select, String[] where, String order, int offset, int limit){
        StringBuilder builder = new StringBuilder();
        FluentQuery fluentQuery = null;
        if(select != null) {
            for (String each : select){
                builder.append(each).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            fluentQuery = LitePal.select(builder.toString());
            builder.setLength(0);
        }
        if(where != null) {
            for (String each : where){
                builder.append(each).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            if(fluentQuery == null) {
                fluentQuery = LitePal.where(builder.toString());
            }else {
                fluentQuery = fluentQuery.where(builder.toString());
            }
            builder.setLength(0);
        }
        if(order != null) {
            if(fluentQuery == null) {
                fluentQuery = LitePal.order(order);
            }else {
                fluentQuery = fluentQuery.order(order);
            }
        }
        if(offset != 0) {
            if(fluentQuery == null) {
                fluentQuery = LitePal.offset(offset);
            }else {
                fluentQuery = fluentQuery.offset(offset);
            }
        }
        if(limit != 0) {
            if(fluentQuery == null) {
                fluentQuery = LitePal.limit(limit);
            }else {
                fluentQuery = fluentQuery.limit(limit);
            }
        }

        List list= null;
        if(fluentQuery == null) {
            list = LitePal.findAll(clazz);
        }else {
            list = fluentQuery.find(clazz);
        }
        return list;
    }
}
