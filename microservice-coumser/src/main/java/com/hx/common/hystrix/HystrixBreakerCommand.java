package com.hx.common.hystrix;

import com.hx.common.util.SpringUtil;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
@Slf4j
public class HystrixBreakerCommand<Req,Res> extends HystrixCommand<HystrixResponseEntity<Res>>{
    /**
     * 输入的参数
     */
    @lombok.Setter
    private Req requestParams;

    /**
     * 调用的class
     */
    @lombok.Setter
    private Class<? extends HystrixCommandHandler<Req, Res>> dealClass;

    private static int timeoutMilliseconds=10000;//默认超时时间为10秒

    public HystrixBreakerCommand(String groupKey,String key) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeoutMilliseconds)));
    }

    public HystrixBreakerCommand(String groupKey,String key,String threadPoolKey) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeoutMilliseconds))
                /* 使用HystrixThreadPoolKey工厂定义线程池名称*/
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey)));
    }

    /**
     *
     * @param groupKey
     * @param key
     * @param threadPoolKey
     * @param timeout
     */
    public HystrixBreakerCommand(String groupKey,String key,String threadPoolKey,int timeout) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout))
                /* 使用HystrixThreadPoolKey工厂定义线程池名称*/
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey)));
    }

    @Override
    protected HystrixResponseEntity<Res> run() throws Exception {
        HystrixResponseEntity<Res> entity=new HystrixResponseEntity<Res>();
        HystrixCommandHandler<Req,Res> hystrixCommandHandler = SpringUtil.getBean(dealClass);
        Res response = hystrixCommandHandler.handle(requestParams);
        entity.setData(response);
        entity.setCode("0000");
        entity.setMsg("第三方接口请求执行成功");
        return entity;
    }

    /**
     * 调用超时时，处理逻辑
     * @return
     */
//    public HystrixResponseEntity<Res> getFallback(){
//        HystrixResponseEntity<Res> entity=new HystrixResponseEntity<Res>();
//        entity.setCode("9999");
//        entity.setMsg("request timeout,it is fallback");
//        log.error("第三方接口调用超时");
//        return entity;
//    }

    public HystrixBreakerCommand<Req, Res> setDealParam(Req requestParams, Class<? extends HystrixCommandHandler<Req, Res>> dealClass) {
        this.requestParams = requestParams;
        this.dealClass = dealClass;
        return this;
    }


}
