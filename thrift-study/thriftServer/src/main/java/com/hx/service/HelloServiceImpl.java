package com.hx.service;

import com.hx.thrift.HelloService;
import org.apache.thrift.TException;

/**
 * thrift同步接口方法
 */
public class HelloServiceImpl implements HelloService.Iface{
    @Override
    public String sayHello(String name) throws TException {
        System.out.println("server ...");
        return "Hello:"+name;
    }
}
