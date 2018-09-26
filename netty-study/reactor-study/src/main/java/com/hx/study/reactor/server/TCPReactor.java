package com.hx.study.reactor.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPReactor implements Runnable {
    private final ServerSocketChannel ssc;
    private final Selector selector;

    public TCPReactor(int port) throws IOException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(port);
        // 在ServerSocketChannel綁定監聽端口
        ssc.socket().bind(addr);
        // 設置ServerSocketChannel為非阻塞
        ssc.configureBlocking(false);
        // ServerSocketChannel向selector註冊一個OP_ACCEPT事件，然後返回該通道的key
        SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
        // 給定key一個附加的Acceptor對象
        sk.attach(new Acceptor(selector, ssc));
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Waiting for new event on port: " + ssc.socket().getLocalPort() + "...");
            try {
                if (selector.select() == 0){
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                dispatch((SelectionKey) (it.next()));
                it.remove();
            }
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) (key.attachment());
        if (r != null){
            r.run();
        }
    }

}
