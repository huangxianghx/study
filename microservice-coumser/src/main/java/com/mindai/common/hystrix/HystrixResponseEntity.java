package com.mindai.common.hystrix;

import lombok.Data;

/**
 * @Description: 熔断器返回对象
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
@Data
public class HystrixResponseEntity<T> {
    T data;
    String code;
    String msg;
}
