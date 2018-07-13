package com.hx.facade.api;

import com.hx.facade.model.HelloRequest;
import com.hx.facade.model.HelloResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
public interface HelloService {
    HelloResponse testHello(HelloRequest request);

    void sayHello();

    void amount(HttpServletRequest request);
}
