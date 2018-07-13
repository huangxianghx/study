package com.hx.service;

import com.hx.api.ProviderService;
import com.hx.facade.api.HelloService;
import com.hx.facade.model.HelloRequest;
import com.hx.facade.model.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
@RestController
public class HelloServiceImpl implements HelloService{
    @Autowired
    private ProviderService providerService;

    @Override
    @RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    public HelloResponse testHello(@RequestBody HelloRequest request) {
        return providerService.generate(request);
    }

    @Override
    @RequestMapping(value = "/sayHello" ,method = RequestMethod.GET)
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    @RequestMapping(value = "/amount" ,method = RequestMethod.POST)
    public void amount(HttpServletRequest request) {
        String amount = request.getParameter("amount");
        System.out.println(amount);
    }
}
