//package com.hx.study.reactor.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.Reactor;
//import reactor.event.Event;
//
//import java.util.HashMap;
//
//@RestController
//public class ReactorController {
//    @Autowired
//    @Qualifier("rootReactor")
//    private Reactor r;
//
//    @Autowired
//    @Qualifier("reportReactor")
//    private Reactor rx;
//
//
//    @RequestMapping("/root")
//    @Transactional
//    @ResponseBody
//    public void chen() {
//        HashMap map=new HashMap();
//        r.notify("hello", Event.wrap(map));
//    }
//
//    @RequestMapping("/report")
//    @Transactional
//    @ResponseBody
//    public void chenzy() {
//        rx.notify("hellos", Event.wrap("好"));
//    }
//}
