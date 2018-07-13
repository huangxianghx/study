package com.hx.facade.api;

import com.hx.facade.model.UserRequest;
import com.hx.facade.model.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月04日
 */
public interface UserService {
    UserResponse getUserInfo(@RequestBody UserRequest request) throws InterruptedException;

//    UserResponse addUser(@RequestBody UserRequest request);
//
//    UserResponse updateUser(@RequestBody UserRequest request);
//
//    void deleteUser(@RequestBody UserRequest request);
}
