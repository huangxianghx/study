package com.hx.service;

import com.hx.facade.api.UserService;
import com.hx.facade.model.UserRequest;
import com.hx.facade.model.UserResponse;
//import com.hx.persistence.mapper.UserMapper;
import com.hx.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年10月17日
 */
@RestController
@Slf4j
public class UserServiceImpl implements UserService {
//    @Autowired
//    UserMapper userMapper;

//    @Cacheable(value = "userInfoCache",keyGenerator = "wiselyKeyGenerator")
//    public void test(){
//
//    }

    @RequestMapping(value = "/getUserInfo" ,method = RequestMethod.POST)
    @ResponseBody
    @Override
//    @Cacheable(value = "userInfoCache",keyGenerator = "wiselyKeyGenerator")
    public UserResponse getUserInfo(@RequestBody UserRequest request) throws InterruptedException {
        UserResponse response=new UserResponse();
//        User user=userMapper.selectByName(request.getName());
//        BeanUtils.copyProperties(user,response);
        Thread.sleep(2000);
        log.info("if i print,i am first");
        return response;
    }

//    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
//    @ResponseBody
//    @CacheEvict(value = "userInfoCache")
//    public UserResponse addUser(@RequestBody UserRequest request) {
//        UserResponse response=new UserResponse();
//        User user=new User();
//        BeanUtils.copyProperties(request,user);
//        userMapper.insert(user);
//        BeanUtils.copyProperties(user,response);
//        return response;
//    }
//
//    @RequestMapping(value = "/updateUser" ,method = RequestMethod.POST)
//    @ResponseBody
//    @CacheEvict(value = "userInfoCache")
//    public UserResponse updateUser(@RequestBody UserRequest request) {
//        UserResponse response=new UserResponse();
//        User user=new User();
//        BeanUtils.copyProperties(request,user);
//        userMapper.updateByName(user);
//        BeanUtils.copyProperties(user,response);
//        return response;
//    }
//
//    @RequestMapping(value = "/deleteUser" ,method = RequestMethod.POST)
//    @ResponseBody
//    @CacheEvict(value = "userInfoCache")
//    public void deleteUser(@RequestBody UserRequest request) {
//        userMapper.deleteByName(request.getName());
//    }
}
