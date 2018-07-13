package com.hx.controller;

import com.hx.service.CloudGrpcClient;
import com.hx.service.HelloWorldClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxiang
 * @Note
 * @date 2017年11月21日 20:43
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    CloudGrpcClient cloudGrpcClient;

    /**
     * grpc客户端调用-集成spring cloud
     * @param name
     * @return
     */
    @RequestMapping(value = "/sayHello" ,method = RequestMethod.GET)
    public String sayHello(@RequestParam String name) {
        logger.info("send==========>{}",name);
        String response = cloudGrpcClient.sayHello(name);
        return response;
    }


    /**
     * grpc客户端调用-原生
     * @param name
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/sayHelloOrigin" ,method = RequestMethod.GET)
    public String sayHelloOrigin(@RequestParam String name) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);
        String response = client.greet("world:" + name);
        client.shutdown();
        return response;
    }
}
