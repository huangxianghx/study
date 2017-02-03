package com.mindai.facade.api;

import com.mindai.facade.model.HelloRequest;
import com.mindai.facade.model.HelloResponse;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
public interface HelloService {
    HelloResponse testHello(HelloRequest request);
}
