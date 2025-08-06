package com.frank;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: maxinhang.
 */
public class ElderlyChat {
    // 对话计数和总次数
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final int TOTAL = 10;

    // 锁对象
    private static final Object liWriteLock = new Object();
    private static final Object zhangWriteLock = new Object();
    // 对话内容常量
    private static final String Z0 = " 吃了没，您吶?";
    private static final String Z3 = " 嗨！吃饱了溜溜弯儿。";
    private static final String Z5 = " 回头去给老太太请安！";
    private static final String L1 = " 刚吃。";
    private static final String L2 = " 您这，嘛去？";
    private static final String L4 = " 有空家里坐坐啊。";

    // 线程池 - 关键修复：使用线程池代替频繁创建新线程
    private static final ExecutorService threadPool = new ThreadPoolExecutor(
            10,                // 核心线程数
            50,                // 最大线程数
            60L, TimeUnit.SECONDS,  // 空闲线程存活时间
            new SynchronousQueue<>(),  // 工作队列
            new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "chat-thread-" + threadNumber.getAndIncrement());
                    t.setDaemon(true);  // 设置为守护线程，程序退出时自动关闭
                    return t;
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()  // 当线程池满时，让提交任务的线程执行任务
    );
    // 请求响应结构体
    static class RequestResponse {
        int serial;
        String payload;

        RequestResponse(int serial, String payload) {
            this.serial = serial;
            this.payload = payload;
        }
    }

    // 序列化发送数据
    private static void writeTo(RequestResponse r, Socket socket, Object lock) throws IOException {
        synchronized (lock) {
            byte[] payloadBytes = r.payload.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + payloadBytes.length);

            // 写入长度(4字节)
            buffer.putInt(4 + payloadBytes.length);
            // 写入序号(4字节)
            buffer.putInt(r.serial);
            // 写入内容
            buffer.put(payloadBytes);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(buffer.array());
            outputStream.flush();
        }
    }

    // 接收数据并反序列化
    private static RequestResponse readFrom(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        //读取长度
        int length = dataInputStream.readInt();
        //读取序号
        int serial = dataInputStream.readInt();
        //读取内容
        byte[] bytes = new byte[length - 4];
        dataInputStream.readFully(bytes);
        return new RequestResponse(serial, new String(bytes));
    }

    // 张大爷监听
    private static void zhangDaYeListen(Socket socket) {
        try {
            while (count.get() < TOTAL) {
                RequestResponse r = readFrom(socket);

                if (r.payload.equals(L2)) {
                    threadPool.execute(() -> {
                        try {
                            writeTo(new RequestResponse(r.serial, Z3), socket, zhangWriteLock);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else if (r.payload.equals(L4)) {
                    threadPool.execute(() -> {
                        try {
                            writeTo(new RequestResponse(r.serial, Z5), socket, zhangWriteLock);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else if (!r.payload.equals(L1)) {
                    System.out.println(" 张大爷听不懂：" + r.payload);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(" 张大爷监听出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 张大爷的说话方法
    private static void zhangDaYeSay(Socket socket) {
        try {
            int nextSerial = 0;
            for (int i = 0; i < TOTAL; i++) {
                writeTo(new RequestResponse(nextSerial++, Z0), socket, zhangWriteLock);
            }
        } catch (IOException e) {
            System.out.println(" 张大爷说话出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 李大爷的监听方法
    private static void liDaYeListen(Socket socket, CountDownLatch latch) {
        try {
            while (count.get() < TOTAL) {
                RequestResponse r = readFrom(socket);

                if (r.payload.equals(Z0)) {
                    synchronized (liWriteLock) {
                        writeTo(new RequestResponse(r.serial, L1), socket, liWriteLock);
                    }
                } else if (r.payload.equals(Z5)) {
                    count.incrementAndGet();
                } else if (!r.payload.equals(Z3)) {
                    System.out.println(" 李大爷听不懂：" + r.payload);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(" 李大爷监听出错：" + e.getMessage());
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    // 李大爷的说话方法
    private static void liDaYeSay(Socket socket) {
        try {
            int nextSerial = 0;
            for (int i = 0; i < TOTAL; i++) {
                writeTo(new RequestResponse(nextSerial++, L2), socket, liWriteLock);
                writeTo(new RequestResponse(nextSerial++, L4), socket, liWriteLock);
            }
        } catch (IOException e) {
            System.out.println(" 李大爷说话出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 启动服务器(张大爷)
    private static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println(" 张大爷在胡同口等着 ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(" 碰见一个李大爷:" + clientSocket.getInetAddress().getHostAddress());

                new Thread(() -> zhangDaYeListen(clientSocket)).start();
                new Thread(() -> zhangDaYeSay(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println(" 服务器出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 启动客户端(李大爷)
    private static void startClient() {
        try (Socket socket = new Socket("127.0.0.1", 9999)) {
            CountDownLatch latch = new CountDownLatch(1);

            new Thread(() -> liDaYeListen(socket, latch)).start();
            new Thread(() -> liDaYeSay(socket)).start();

            latch.await();
        } catch (IOException | InterruptedException e) {
            System.out.println(" 客户端出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 启动服务器线程
        new Thread(ElderlyChat::startServer).start();

        // 等待服务器启动
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // 启动客户端并计时
        long startTime = System.currentTimeMillis();
        startClient();
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println(" 耗时: " + elapsedTime + " 毫秒");
    }
}
