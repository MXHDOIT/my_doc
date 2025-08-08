package com.frank.network;

import com.frank.network.constant.HttpConstant;
import com.frank.network.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author: maxinhang.
 */
public class NioBlockingHttpClient {
    private SocketChannel socketChannel;
    private String host;

    public static void main(String[] args) throws IOException {

        for (String host: HttpConstant.HOSTS) {

            NioBlockingHttpClient client = new NioBlockingHttpClient(host, HttpConstant.PORT);
            client.request();

        }

    }

    public NioBlockingHttpClient(String host, int port) throws IOException {
        this.host = host;
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(10000);  // 增加读取超时到10秒
        SocketAddress remote = new InetSocketAddress(this.host, port);
        this.socketChannel.connect(remote);
    }

    public void request() throws IOException {
        PrintWriter pw = getWriter(socketChannel.socket());
        BufferedReader br = getReader(socketChannel.socket());

        pw.write(HttpUtil.compositeRequest(host));
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println(msg);
        }
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }
}
