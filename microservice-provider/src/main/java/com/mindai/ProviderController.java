package com.mindai;

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
    public String hello(@RequestBody String name) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("hello:"+name);
        return "hello"+name;
    }
}
