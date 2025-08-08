package com.frank.network;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author: maxinhang.
 */
public class Attachment {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private boolean isReadMode;
    private ByteBuffer buffer;

    // getter & setter
    public AsynchronousServerSocketChannel getServer() {
        return this.server;
    }

    public void setServer(AsynchronousServerSocketChannel server) {
        this.server= server;
    }

    public AsynchronousSocketChannel getClient() {
        return this.client;
    }

    public void setClient(AsynchronousSocketChannel client) {
        this.client = client;
    }

    public boolean isReadMode() {
        return this.isReadMode;
    }

    public void setReadMode(boolean readMode) {
        this.isReadMode = readMode;
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

}
