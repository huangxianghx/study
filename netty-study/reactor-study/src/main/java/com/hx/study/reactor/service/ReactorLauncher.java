//package com.hx.study.reactor.service;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import reactor.bus.EventBus;
//
//import static reactor.bus.selector.Selectors.$;
//
///**
// * 关联事件类型与事件处理程序，通过事件标签进行绑定，借助实现 SpringBoot CommandLineRunner 接口，实现启动自动绑定
// */
//@Component
//public class ReactorLauncher implements InitializingBean {
//    @Autowired
//    private EventBus eventBus;
//
//    @Autowired
//    private ReceiverHandle receiverHandle;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        eventBus.on($("number"), receiverHandle);
//    }
//}
