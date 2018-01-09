package com.hx.service;

import com.hx.entity.UserInfo;
import com.hx.mapper.UserInfoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangxiang
 * @Note
 * @date 2017年11月22日 15:08
 */
@Service
public class UserInfoService {
    @Autowired(required = true)
    UserInfoMapping userInfoMapping;

    /**
     * 根据uid获取user_info
     * @param uid
     * @return
     */
    public UserInfo getUserInfoByUid(String uid){
        return userInfoMapping.getUserInfo(uid);
    }
}
