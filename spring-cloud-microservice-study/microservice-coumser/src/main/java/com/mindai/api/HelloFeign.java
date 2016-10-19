package com.mindai.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年10月17日
 */
@FeignClient(name = "microservice-provider",fallback = HelloFeign.HelloFeignFallback.class)
public interface HelloFeign {
    @RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    public String hello(@RequestParam("name") String name);

    @Component
    static class HelloFeignFallback implements HelloFeign {
        @Override
        public String hello(@RequestParam("name") String name) {
            System.out.println("i am fail.");
            return "fail:"+name;
        }
    }
}
