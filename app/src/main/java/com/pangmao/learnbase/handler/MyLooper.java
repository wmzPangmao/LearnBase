package com.pangmao.learnbase.handler;

/**
 * @author wangmingzhi
 */
public class MyLooper {
    // 每个线程中的Looper对象其实是一个ThreadLocal，即线程本地存储(TLS)对象
    static final ThreadLocal<MyLooper> sThreadLocal = new ThreadLocal<>();
    // Looper内的消息队列
    MyMessageQueue mQueue;
    // 当前线程
    Thread mThread;

    private MyLooper() {
        mQueue = new MyMessageQueue();
        mThread = Thread.currentThread();
    }

    /**
     * 启动looper, 让MessageQueue运行
     */
    public static void loop(){
        final MyLooper me = myLooper();
        final MyMessageQueue queue = me.mQueue;

        for (;;){
            MyMessage msg = queue.next();
            if (msg != null){
                if (msg.target == null){
                    return;
                }
                // 将真正的处理工作交给message的target，即后面要讲的handler
                msg.target.dispatchMessage(msg);
            }else {
                return;
            }
        }
    }

    /**
     * 准备Looper, 将对象存储在ThreadLocal中
     * 一个 Thread只能又一个Looper对象
     */
    public static void prepare(){
        if (sThreadLocal.get() == null){
            sThreadLocal.set(new MyLooper());
        }
    }

    /**
     * 从ThreadLocal中获取当前线程的Looper
     * @return   MyLooper
     */
    public static MyLooper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 发送一个 target为null的消息,结束loop循环
     */
    public static void quit(){
        // 创建一个空的message，它的target为NULL，表示结束循环消息
        MyMessage msg = new MyMessage();
        // 发出消息
        myLooper().mQueue.enqueueMessage(msg);
    }
}
