package com.hx.interceptor;

import com.hx.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class RateLimitInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    RedisTemplate<String,Integer> integerRedisTemplate;
    protected static final Logger log = LoggerFactory.getLogger(RateLimitInterceptor.class);

    private PathMatcher matcher = new AntPathMatcher();
    private static final String ERROR_URL="/error";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if(matcher.match(url,ERROR_URL)){
            return true;
        }
        //针对IP做限制
        String ip= IpUtils.getClientIpAddress(httpServletRequest);
        Integer ipCount = integerRedisTemplate.opsForValue().get(ip);
        if(ipCount==null){
            integerRedisTemplate.opsForValue().set(ip,1,1, TimeUnit.MINUTES);
        }else if(ipCount<10){
            integerRedisTemplate.opsForValue().increment(ip,1);
        }else{
            log.error("同一Ip最近时间段访问太频繁，拒绝访问");
            return false;
        }
        String urlRedis=stringRedisTemplate.opsForValue().get("url");
        log.info("ip:"+ip);
        log.info("redisUrl:"+urlRedis);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
