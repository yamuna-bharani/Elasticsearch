package com.pkg.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by via on 7/17/18.
 */
public class ExecutorUtil {

    private static int threadCount = 5;

    private static ExecutorService threadPool;

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    public static void init() {
        threadPool = new ThreadPoolExecutor(threadCount, threadCount,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
