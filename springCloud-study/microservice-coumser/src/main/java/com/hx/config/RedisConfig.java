package com.hx.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,String> StringRedisTemplate(RedisConnectionFactory factory){
        return initRedisTemplate(String.class,factory);
    }

    @Bean
    public RedisTemplate<String,Integer> IntegerRedisTemplate(RedisConnectionFactory factory){
        return initRedisTemplate(Integer.class,factory);
    }

    private <T>void setSerializer(Class<T> clz,RedisTemplate<String, T> template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(clz);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }

    private <T> RedisTemplate<String, T>  initRedisTemplate(Class<T> clz,RedisConnectionFactory factory){
        RedisTemplate<String,T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();

        this.setSerializer(clz,redisTemplate);

        return redisTemplate;
    }
}
