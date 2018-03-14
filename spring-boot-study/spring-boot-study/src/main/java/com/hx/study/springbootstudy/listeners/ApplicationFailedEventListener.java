package com.hx.study.springbootstudy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * SpringApplication 发布FailedEvent时监听处理
 * SpringApplication所处时期:spring boot启动异常时执行事件
 * 在异常发生时，最好是添加虚拟机对应的钩子进行资源的回收与释放，能友善的处理异常信息。
 */
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationFailedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        logger.info("logger: FailedEvent listener......");
    }
}
