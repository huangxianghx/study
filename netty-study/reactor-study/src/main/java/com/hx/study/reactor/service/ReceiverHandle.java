//package com.hx.study.reactor.service;
//
//import com.hx.study.reactor.config.HuyaLogger;
//import org.springframework.stereotype.Service;
//import reactor.bus.Event;
//import reactor.fn.Consumer;
//
///**
// * 编写事件处理程序，需要实现 Consumer<Event<T>> 接口，其中 T 是处理程序接收的数据类型，要根据具体业务设置，这里使用 Integer
// */
//@Service
//public class ReceiverHandle implements Consumer<Event<Integer>> {
//    private static final HuyaLogger LOG = HuyaLogger.getLogger(ReceiverHandle.class);
//
//    @Override
//    public void accept(Event<Integer> integerEvent) {
//        LOG.info("Process number " + integerEvent.getData());
//    }
//}
