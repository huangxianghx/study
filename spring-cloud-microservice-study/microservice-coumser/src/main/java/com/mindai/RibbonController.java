package com.mindai;

import com.mindai.api.HelloFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年10月17日
 */
@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloFeign helloFeign;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String name) {
        String resp=null;
        try{
            long begintime=System.currentTimeMillis();
            resp=helloFeign.hello(name);
            long endtime=System.currentTimeMillis();
            long time=endtime-begintime;
            System.out.println("feign:"+time);
            begintime=System.currentTimeMillis();
            resp=restTemplate.postForEntity("http://microservice-provider/hello", name, String.class).getBody();
            endtime=System.currentTimeMillis();
            time=endtime-begintime;
            System.out.println("ribbon"+time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }


//    public String fallback(@RequestBody String name){
//        System.out.println("i am fail");
//        return "fail:"+name;
//    }
}
