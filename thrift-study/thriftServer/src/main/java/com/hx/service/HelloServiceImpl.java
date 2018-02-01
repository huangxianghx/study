package com.hx.service;

import com.hx.thrift.HelloService;
import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface{
    @Override
    public String sayHello(String name) throws TException {
        System.out.println("server ...");
        return "Hello:"+name;
    }
}
