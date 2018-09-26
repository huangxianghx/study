package com.hx.study.reactor.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
    private final ServerSocketChannel ssc;
    private final Selector selector;

    public Acceptor(Selector selector, ServerSocketChannel ssc) {
        this.ssc = ssc;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " is connected.");
            if (sc != null) {
                sc.configureBlocking(false); // 設置為非阻塞
                SelectionKey sk = sc.register(selector, SelectionKey.OP_READ);
                selector.wakeup();
                sk.attach(new TCPHandler(sk, sc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
