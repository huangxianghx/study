package com.mindai;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年10月17日
 */
@RestController
public class ProviderController {

    @RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody String name){
        System.out.println("hello:"+name);
        return "hello"+name;
    }
}
