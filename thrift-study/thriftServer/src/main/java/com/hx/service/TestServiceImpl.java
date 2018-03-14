package com.hx.service;

import com.hx.thrift.TestService;
import org.apache.thrift.TException;

public class TestServiceImpl implements TestService.Iface{
    @Override
    public void ping() throws TException {
        System.out.println("ping");
    }

    @Override
    public String sayHello(String name) throws TException {
        System.out.println("server ...");
        return "Hello:"+name;
    }
}
