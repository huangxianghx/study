package com.hx.study.springbootstudy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringApplication 发布ReadyEvent时监听处理
 * SpringApplication所处时期:spring boot上下文context创建完成，spring中的bean是加载完成的。
 */
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("logger: ReadyEvent listener......");
        ConfigurableApplicationContext applicationContext = applicationReadyEvent.getApplicationContext();

    }
}
