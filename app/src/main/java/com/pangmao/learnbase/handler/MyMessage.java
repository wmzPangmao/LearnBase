package com.pangmao.learnbase.handler;

/**
 * @author wangmingzhi
 */
public class MyMessage {

    MyHandler target;

    Object obj;

    @Override
    public String toString() {
        return obj.toString();
    }
}
