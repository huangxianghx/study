package com.mindai.facade.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月04日
 */
@Data
public class UserResponse implements Serializable {
    private static final long serialVersionUID = -1L;
    String name;
    String sex;
    String age;
}
