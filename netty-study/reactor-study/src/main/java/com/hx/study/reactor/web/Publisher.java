//package com.hx.study.reactor.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.bus.Event;
//import reactor.bus.EventBus;
//import reactor.core.Reactor;
//
//@RestController
//public class Publisher {
//    @Autowired
//    EventBus eventBus;
//
//    @Autowired
//    @Qualifier("rootReactor")
//    private Reactor r;
//
//    @Autowired
//    @Qualifier("reportReactor")
//    private Reactor rx;
//
//    /**
//     * 编写事件发送程序，发送程序发送事件时需要指定标签，用以区分不同事件，以便交给不同的处理程序处理
//     * @throws InterruptedException
//     */
//    @RequestMapping("reactor/demo")
//    @ResponseBody
//    public void publish() throws InterruptedException {
//        for (int i = 0; i < 1000; i++) {
//            eventBus.notify("number", Event.wrap(i));
//        }
//    }
//
//}
