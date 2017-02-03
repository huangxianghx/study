package com.mindai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年10月17日
 */
@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;
    public String hello(String name) {
        // http://服务提供者的serviceId/url
        return restTemplate.postForEntity("http:/localhost:8888/hello", "huangxiang",String.class).getBody();
    }
}