//package com.hx.study.reactor.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import reactor.core.Reactor;
//import reactor.event.Event;
//import reactor.spring.annotation.Selector;
//
//@Service
//public class ReactorHandle {
//    @Autowired
//    @Qualifier("rootReactor")
//    private Reactor reactor ;
//    @Autowired
//    @Qualifier("reportReactor")
//    private Reactor reactorxx ;
//
//    @Selector(value="hello",reactor="@rootReactor")
//    public void handleTestTopic(Event<String> evt)throws Exception{
//        System.out.println("************");
//    }
//
//    @Selector(value="hellos",reactor="@reportReactor")
//    public void handleTestTopics(Event<String> evt)throws Exception{
//        System.out.println("xxxxxx**********");
//        String data = evt.getData();
//        System.out.println(data);
//    }
//}
