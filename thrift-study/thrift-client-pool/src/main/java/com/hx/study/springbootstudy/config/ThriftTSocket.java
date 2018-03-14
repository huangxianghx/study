package com.hx.study.springbootstudy.config;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import java.net.Socket;

public class ThriftTSocket extends TSocket {
    public ThriftTSocket(Socket socket) throws TTransportException {
        super(socket);
    }

    public ThriftTSocket(String host, int port) {
        super(host, port);
    }

    public ThriftTSocket(String host, int port, int timeout) {
        super(host, port, timeout);
    }

    public ThriftTSocket(String host, int port, int socketTimeout, int connectTimeout) {
        super(host, port, socketTimeout, connectTimeout);
    }
}
