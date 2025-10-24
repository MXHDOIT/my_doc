package com.frank;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: maxinhang.
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1));
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
