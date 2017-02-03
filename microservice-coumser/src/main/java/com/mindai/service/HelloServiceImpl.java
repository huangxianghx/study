package com.mindai.service;

import com.mindai.api.ProviderService;
import com.mindai.facade.api.HelloService;
import com.mindai.facade.model.HelloRequest;
import com.mindai.facade.model.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public HelloResponse testHello(@RequestBody HelloRequest request) {
        return providerService.generate(request);
    }
}
