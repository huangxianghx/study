package com.hx.study.reactor.web;

import com.hx.study.reactor.service.Clown;
import com.hx.study.reactor.service.Viewer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TestController {

    /**
     * 迭代器测试
     */
    @RequestMapping("/iterator")
    @ResponseBody
    public void iterator() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(1/iterator.next());
        }
    }

    /**
     * 观察者模式测试
     */
    @RequestMapping("/observer")
    @ResponseBody
    public void observer() {
        //来了一个小丑
        Clown clown = new Clown();
        //观众入场了
        for (int i = 0; i < 20; i++) {
            Viewer v = new Viewer(i);
            clown.addObserver(v);//配置观察者
            System.out.println("座号为" + v.getSeatNo() + "的观众入座");
        }
        //小丑开始表演
        clown.perform();
        //小丑表演完毕，退场
        clown.exit();
    }

    /**
     * testSms
     */
    @RequestMapping("/testSms")
    @ResponseBody
    public void testSms() {
//        System.out.println(smsService.syncSendByPhone("13710162138","test"));
//        System.out.println(smsService.asyncSendByPhone("13710162138","test"));
    }
}
