package com.hx.service;

import com.hx.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

public class AsyncHelloClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;
    public void startClient(String userName) throws Exception{
        TNonblockingTransport nonblockingTransport = null;
        try {
            TProtocolFactory protocolFactory =  new TBinaryProtocol.Factory();
            TAsyncClientManager asyncClientManager = new TAsyncClientManager();
            nonblockingTransport = new TNonblockingSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            HelloService.AsyncClient client = new HelloService.AsyncClient(protocolFactory,asyncClientManager,nonblockingTransport );
            nonblockingTransport.startConnect();
            HelloServiceCallback helloServiceCallback=new HelloServiceCallback();
            client.sayHello(userName,helloServiceCallback);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != nonblockingTransport) {
                nonblockingTransport.finishConnect();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        AsyncHelloClient client = new AsyncHelloClient();
        client.startClient("Vilarsail");
    }

    public class HelloServiceCallback implements AsyncMethodCallback<String> {

        @Override
        public void onComplete(String s) {
            System.out.println("Complete:"+s);
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }
    }
}
