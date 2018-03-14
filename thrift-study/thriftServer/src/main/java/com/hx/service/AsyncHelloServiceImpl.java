package com.hx.service;

import com.hx.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * thrift异步接口方法
 */
public class AsyncHelloServiceImpl implements HelloService.AsyncIface{
    @Override
    public void sayHello(String name, AsyncMethodCallback<String> resultHandler) throws TException {
        System.out.println("server ...");
        resultHandler.onComplete("success");
    }
}
