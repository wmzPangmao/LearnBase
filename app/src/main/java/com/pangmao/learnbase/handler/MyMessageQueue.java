package com.pangmao.learnbase.handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wangmingzhi
 */
public class MyMessageQueue {

    private BlockingQueue<MyMessage> queue;
    private final int MAXCOUNT = 20;

    public MyMessageQueue() {
        queue = new ArrayBlockingQueue(MAXCOUNT);
    }

    /**
     * 从消息队列添加消息
     * 入队,根据时间排序,当队列满的时候会出现阻塞
     * 直到用户通过next取出消息.每次入队后都会唤醒 next方法
     * @param msg   消息
     * @return      是否成功
     */
    public void enqueueMessage(MyMessage msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从消息队列取消息
     * 通过Looper.loop() 循环从队列中取消息
     * 当队列为空的时候会出现阻塞
     * @return          消息对象
     */
    public MyMessage next(){
        MyMessage msg = null;
        try {
            msg = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
