//package com.hx.study.reactor.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.Environment;
//import reactor.core.Reactor;
//import reactor.core.spec.Reactors;
//import reactor.spring.context.config.EnableReactor;
//
//@Configuration
//@EnableReactor
//public class MyBeans {
//    @Bean
//    Environment env() {
//        return new Environment();
//    }
////
////    @Bean
////    EventBus createEventBus(Environment env) {
////        return EventBus.create(env, Environment.THREAD_POOL);//同步线程
//////        return EventBus.create(env, "sync");//异步线程
////    }
//    @Bean(name="rootReactor")
//    public Reactor rootReactor(Environment env){
//        return Reactors.reactor().env(env).get();
//    }
//
//    @Bean(name = "reportReactor")
//    public Reactor reportReactor(Environment env) {
//        return Reactors.reactor().env(env).get();
//    }
//}
