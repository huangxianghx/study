package com.hx.study.springbootstudy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringApplication 发布PreparedEvent时监听处理
 * SpringApplication所处时期:spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 * 在获取完上下文后，可以将上下文传递出去做一些额外的操作。
 * 在该监听器中是无法获取自定义bean并进行操作的。
 */
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        //获取上下文
        ConfigurableApplicationContext applicationContext = applicationPreparedEvent.getApplicationContext();
        passContextInfo(applicationContext);
        logger.info("logger: PreparedEvent listener......");
    }

    /**
     * 传递上下文
     * @param cac
     */
    private void passContextInfo(ApplicationContext cac) {
        //do something()
    }
}
