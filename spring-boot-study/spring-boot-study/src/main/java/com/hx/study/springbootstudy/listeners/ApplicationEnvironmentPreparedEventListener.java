package com.hx.study.springbootstudy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

/**
 * SpringApplication 发布EnvironmentPreparedEvent时监听处理
 * SpringApplication所处时期:spring boot 对应Enviroment已经准备完毕，但此时上下文context还没有创建。
 * 在该监听中获取到ConfigurableEnvironment后可以对配置信息做操作
 * 例如：修改默认的配置信息，增加额外的配置信息
 */
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationEnvironmentPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        //获取环境配置
        ConfigurableEnvironment environment = applicationEnvironmentPreparedEvent.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        logger.info("logger: EnvironmentPreparedEvent listener......");
    }
}
