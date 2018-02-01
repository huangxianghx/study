package com.hx.service;

import com.github.phantomthief.thrift.client.ThriftClient;
import com.github.phantomthief.thrift.client.impl.ThriftClientImpl;
import com.github.phantomthief.thrift.client.pool.ThriftServerInfo;
import com.github.phantomthief.thrift.client.pool.impl.DefaultThriftConnectionPoolImpl;
import com.hx.thrift.HelloService;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Arrays;
import java.util.function.Function;

public class Test1 {
    public static void main(String[] args) throws TException {
//        try{
//            ThriftClient thriftClient = new ThriftClientImpl(() -> Arrays.asList(//
//                    ThriftServerInfo.of("localhost", 8090)
//            ));
//            HelloService.Iface iFace = thriftClient.iface(HelloService.Client.class);
//            String response = iFace.sayHello("hello");
//            System.out.println("response===="+response);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
//        TProtocol protocol = new TBinaryProtocol(transport);
//        HelloService.Client client = new HelloService.Client(protocol);


        GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();
        Function<ThriftServerInfo, TTransport> transportProvider = info -> {
            TSocket socket = new TSocket(info.getHost(), info.getPort(),30000);
            TFramedTransport transport = new TFramedTransport(socket);
            return transport;
        };
        ThriftClient customizeThriftClient = new ThriftClientImpl(() -> Arrays.asList(//
                ThriftServerInfo.of("localhost", 8090)
        ), new DefaultThriftConnectionPoolImpl(poolConfig, transportProvider));
        String response = customizeThriftClient.iface(HelloService.Client.class).sayHello("hello world.");
        System.out.println("response===="+response);
    }

}
