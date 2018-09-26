package com.hx.study.reactor.server;

import java.io.IOException;

public class TCPReactorServer {
    public static void main(String[] args) throws IOException {
        TCPReactor tcpReactor = new TCPReactor(1333);
        tcpReactor.run();
    }
}
