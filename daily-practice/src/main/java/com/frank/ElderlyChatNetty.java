package com.frank;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ElderlyChatNetty {
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final int TOTAL = 10;  // 为了演示，暂时改为10次
    private static final int PORT = 9999;

    private static final String Z0 = " 吃了没，您吶?";
    private static final String Z3 = " 嗨！吃饱了溜溜弯儿。";
    private static final String Z5 = " 回头去给老太太请安！";
    private static final String L1 = " 刚吃。";
    private static final String L2 = " 您这，嘛去？";
    private static final String L4 = " 有空家里坐坐啊。";

    static class RequestResponse {
        int serial;
        String payload;

        RequestResponse(int serial, String payload) {
            this.serial = serial;
            this.payload = payload;
        }
    }

    static class RequestResponseEncoder extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
            if (msg instanceof RequestResponse) {
                RequestResponse res = (RequestResponse) msg;
                byte[] payloadBytes = res.payload.getBytes(CharsetUtil.UTF_8);
                int serialLength = 4; // 序号占4字节
                int totalLength = serialLength + payloadBytes.length;

                // 严格校验长度合法性（必须为正数）
                if (totalLength <= 0) {
                    promise.setFailure(new IllegalArgumentException("消息长度无效"));
                    return;
                }

                ByteBuf buf = ctx.alloc().buffer(4 + totalLength); // 4字节长度 + 内容
                buf.writeInt(totalLength); // 写入总长度（序号+payload）
                buf.writeInt(res.serial); // 写入序号
                buf.writeBytes(payloadBytes); // 写入内容

                ctx.write(buf, promise);
            } else {
                ctx.write(msg, promise);
            }
        }
    }

    static class RequestResponseDecoder extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) {
            // 此时buf已被LengthFieldBasedFrameDecoder处理为完整帧
            try {
                // 读取序号（4字节）
                int serial = buf.readInt();
                // 读取payload（剩余所有字节）
                byte[] payloadBytes = new byte[buf.readableBytes()];
                buf.readBytes(payloadBytes);
                String payload = new String(payloadBytes, CharsetUtil.UTF_8);

                ctx.fireChannelRead(new RequestResponse(serial, payload));
            } catch (Exception e) {
                System.err.println("解码失败：" + e.getMessage());
                // 出现异常时，关闭连接避免后续错误
                ctx.close();
            }
        }
    }

    // 张大爷的处理器
    static class ZhangDaYeHandler extends ChannelInboundHandlerAdapter {
        private int nextSerial = 0;

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("张大爷：与李大爷碰面了，开始聊天...");
            zhangDaYeSay(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            RequestResponse req = (RequestResponse) msg;
            System.out.println("张大爷听到：" + req.payload);

            if (req.payload.equals(L2)) {
                ctx.executor().execute(() -> {
                    RequestResponse response = new RequestResponse(req.serial, Z3);
                    System.out.println("张大爷说：" + response.payload);
                    ctx.writeAndFlush(response);
                });
            } else if (req.payload.equals(L4)) {
                ctx.executor().execute(() -> {
                    RequestResponse response = new RequestResponse(req.serial, Z5);
                    System.out.println("张大爷说：" + response.payload);
                    ctx.writeAndFlush(response);
                });
            } else if (!req.payload.equals(L1)) {
                System.out.println("张大爷听不懂：" + req.payload);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            System.err.println("张大爷出错：");
            cause.printStackTrace();
            if (count.get() < TOTAL) {
                ctx.close();
            }
        }

        private void zhangDaYeSay(ChannelHandlerContext ctx) {
            if (count.get() < TOTAL && ctx.channel().isActive()) {
                RequestResponse msg = new RequestResponse(nextSerial++, Z0);
                System.out.println("张大爷说：" + msg.payload);

                ctx.writeAndFlush(msg)
                        .addListener((ChannelFuture future) -> {
                            if (future.isSuccess())
                                this.zhangDaYeSay(ctx);
                            else {
                                System.err.println("张大爷发送失败：" + future.cause());
                            }
                        });
            }
        }
    }

    // 李大爷的处理器
    static class LiDaYeHandler extends ChannelInboundHandlerAdapter {
        private int nextSerial = 0;
        private final CountDownLatch latch;

        public LiDaYeHandler(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("李大爷：碰到张大爷了，开始聊天...");
            liDaYeSay(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            RequestResponse req = (RequestResponse) msg;
            System.out.println("李大爷听到：" + req.payload);

            if (req.payload.equals(Z0)) {
                RequestResponse response = new RequestResponse(req.serial, L1);
                System.out.println("李大爷说：" + response.payload);
                ctx.writeAndFlush(response);
            } else if (req.payload.equals(Z5)) {
                int current = count.incrementAndGet();
                System.out.println("===== 第 " + current + " 次碰面结束 =====");
                if (current >= TOTAL) {
                    latch.countDown();
                }
            } else if (!req.payload.equals(Z3)) {
                System.out.println("李大爷听不懂：" + req.payload);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            System.err.println("李大爷出错：");
            cause.printStackTrace();
            ctx.close();
            latch.countDown();
        }

        private void liDaYeSay(ChannelHandlerContext ctx) {
            if (count.get() < TOTAL && ctx.channel().isActive()) {
                // 先发送L2
                RequestResponse msg1 = new RequestResponse(nextSerial++, L2);
                System.out.println("李大爷说：" + msg1.payload);

                ctx.writeAndFlush(msg1)
                        .addListener((ChannelFuture future) -> {
                            if (future.isSuccess()) {
                                // L2发送成功后再发送L4
                                ctx.executor().schedule(() -> {
                                    RequestResponse msg2 = new RequestResponse(nextSerial++, L4);
                                    System.out.println("李大爷说：" + msg2.payload);
                                    ctx.writeAndFlush(msg2)
                                            .addListener((ChannelFuture f) -> {
                                                if (f.isSuccess()) {
                                                    ctx.executor().schedule(() -> liDaYeSay(ctx), 1000, java.util.concurrent.TimeUnit.MILLISECONDS);
                                                }
                                            });
                                }, 500, java.util.concurrent.TimeUnit.MILLISECONDS);
                            }
                        });
            }
        }
    }

    private static void startServer() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            // 添加Netty内置的长度字段解码器，自动处理半包/粘包
                            // 配置：长度字段占4字节，位于帧开头，值为"序号+payload"的总长度
                            p.addLast(new LengthFieldBasedFrameDecoder(
                                    Integer.MAX_VALUE, // 最大帧长度
                                    0, // 长度字段偏移量（从0开始）
                                    4, // 长度字段占4字节
                                    0, // 长度字段后到内容的偏移量（0，即紧跟长度字段）
                                    4  // 跳过的字节数（跳过长度字段本身）
                            ));
                            p.addLast(new RequestResponseEncoder());
                            p.addLast(new RequestResponseDecoder());
                            p.addLast(new ZhangDaYeHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(PORT).sync();
            System.out.println(" 张大爷在胡同口等着 ...");
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static void startClient(CountDownLatch latch) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            // 客户端同样需要处理半包/粘包
                            p.addLast(new LengthFieldBasedFrameDecoder(
                                    Integer.MAX_VALUE, 0, 4, 0, 4
                            ));
                            p.addLast(new RequestResponseEncoder());
                            p.addLast(new RequestResponseDecoder());
                            p.addLast(new LiDaYeHandler(latch));
                        }
                    });

            ChannelFuture f = b.connect("127.0.0.1", PORT).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                startServer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(1000);

        CountDownLatch latch = new CountDownLatch(1);
        long startTime = System.currentTimeMillis();

        startClient(latch);
        latch.await();

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("总耗时: " + elapsedTime + " 毫秒");
        System.out.println("总共对话次数: " + count.get());

        System.exit(0);
    }
}
