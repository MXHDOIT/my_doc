package com.frank;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: maxinhang.
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        Thread thread = new Thread(()->{
            reentrantLock.lock();
            try {
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reentrantLock.unlock();
        });

        thread.start();
        Thread.sleep(100000L);
        reentrantLock.unlock();

    }
}
