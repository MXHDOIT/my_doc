package com.frank;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author: maxinhang.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper connection = new ZooKeeper("127.0.0.1:2181", 5000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected)
                System.out.println("连接成功");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        byte[] data = connection.getData("/hadoop", new MyWatcher(), null);
        System.out.println(new String(data));
        System.out.println(connection.getSessionId());
        Thread.sleep(10000000);
    }

    static class MyWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.getType());
        }
    }
}