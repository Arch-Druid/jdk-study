package com.easyimpl.jdk.study.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EventSourceObject
 *
 * @author yupeng10@baidu.com
 * @since 1.0
 */
public class EventSourceObject {
    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger sum = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(20000L);
                    System.out.println(sum.addAndGet(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(2);
    }
}
