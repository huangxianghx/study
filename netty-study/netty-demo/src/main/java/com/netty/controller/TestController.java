package com.netty.controller;

import com.netty.client.EchoNettyClient;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @RequestMapping("/sendMessage")
    public String test(HttpServletRequest request){
        EchoNettyClient client = new EchoNettyClient();
        Channel channel = client.getClient();
        if(channel!=null){
            channel.writeAndFlush(request.getParameter("message"));
        }
        return "test";
    }
}
