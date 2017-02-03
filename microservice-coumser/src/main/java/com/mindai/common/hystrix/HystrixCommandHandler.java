package com.mindai.common.hystrix;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
public interface HystrixCommandHandler<Req,Res> {
    Res handle(Req req) throws Exception;
}
