//package com.hx.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.integration.support.MessageBuilder;
//
//
///**
// * @author huangxiang
// * @Note
// * @date 2017年12月20日 20:35
// */
//@EnableBinding(Source.class)
//public class KafkaSender {
//
//    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
//
//    @Autowired
//    private Source source;
//
//    public void sendMessage(String message) {
//        try {
//            source.output1().send(MessageBuilder.withPayload("message: " + message).build());
//        } catch (Exception e) {
//            logger.info("消息发送失败，原因："+e);
//            e.printStackTrace();
//        }
//    }
//}