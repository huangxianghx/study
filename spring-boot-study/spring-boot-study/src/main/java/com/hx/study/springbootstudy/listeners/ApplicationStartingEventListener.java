package com.hx.study.springbootstudy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * SpringApplication 发布StartingEvent时监听处理
 * SpringApplication所处时期:spring boot启动开始时执行的事件
 */
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent>{
    private Logger logger = LoggerFactory.getLogger(ApplicationStartingEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        logger.info("logger: StartingEvent listener......");
    }
}
