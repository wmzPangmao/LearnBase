package com.pangmao.learnbase.handler;

/**
 * @author wangmingzhi
 */
public class MyHandler {
    //与Looper关联消息队列
    MyMessageQueue mQueue;
    //关联的Looper
    MyLooper myLooper;

    public MyHandler() {
        // 默认将关联当前线程的looper
        myLooper = MyLooper.myLooper();
        //重要！！！直接把关联looper的MQ作为自己的MQ，因此它的消息将发送到关联looper的MQ上
        mQueue = myLooper.mQueue;
    }

    /**
     * 发送消息
     * @param msg   消息
     */
    public final void sendMessage(MyMessage msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    /**
     * 处理消息
     * @param msg   消息
     */
    public void dispatchMessage(MyMessage msg) {
        handleMessage(msg);
    }

    /**
     * 处理消息的实现方法
     * @param msg       消息
     */
    public void handleMessage(MyMessage msg) {
    }

}
