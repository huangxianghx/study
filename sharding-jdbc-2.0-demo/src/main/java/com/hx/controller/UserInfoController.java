package com.hx.controller;

import com.hx.entity.UserInfo;
import com.hx.service.UserInfoService;
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
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value = "/hello" ,method = RequestMethod.POST)
    public String sayHello(@RequestParam String uid) {
        UserInfo userInfo = userInfoService.getUserInfoByUid(uid);
        return userInfo.toString();
    }
}
