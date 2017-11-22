package com.hx.controller;

import com.hx.service.CloudGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxiang
 * @Note
 * @date 2017年11月21日 20:43
 */
@RestController
public class HelloController {
    @Autowired
    CloudGrpcClient cloudGrpcClient;

    @RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    public String sayHello() {
        cloudGrpcClient.sayHello("123");
        return null;
    }

}
