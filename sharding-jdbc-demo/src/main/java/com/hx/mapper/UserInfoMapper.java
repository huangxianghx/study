package com.hx.mapper;

import com.hx.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 */
@Mapper
public interface UserInfoMapper {

    @Select("SELECT * FROM `user_info` WHERE uid = #{uid}")
    UserInfo getUserInfo(@Param("uid") String uid);

}
