package com.chuanyunbang.crawer.util;

import java.util.concurrent.*;

/**
 * @author Luffy
 * @date 2018/7/13
 * @description 线程工厂
 */
public class ThreadFactory {

    private static int THREAD_NUMS;

    private static final ArrayBlockingQueue queue = new ArrayBlockingQueue(1000,false);

    public static ThreadPoolExecutor executor;

    static {
        THREAD_NUMS = Runtime.getRuntime().availableProcessors()+1;
        executor = new ThreadPoolExecutor(THREAD_NUMS,THREAD_NUMS,0L, TimeUnit.MILLISECONDS,queue, Executors.defaultThreadFactory());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private static ThreadFactory threadFactory;

    private ThreadFactory() {
    }

    public static final ThreadFactory getInstance(){
        if(threadFactory == null){
            threadFactory = new ThreadFactory();
        }
        return threadFactory;
    }

    public static Future execute(Runnable runnable){
        return executor.submit(runnable);
    }
}
