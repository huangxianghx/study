package com.hx.controller;

import com.hx.service.ZookeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxiang
 * @Note
 * @date 2017年11月22日 16:47
 */
@RestController
public class ZookeeperController {
    @Autowired
    ZookeeperService zookeeperService;

    @RequestMapping(value = "/getZNodeByKey" ,method = RequestMethod.POST)
    public String sayHello(@RequestParam String key) {
        String zNode = zookeeperService.getZNodeByKey(key);
        return zNode;
    }
}
