package com.hx.register;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class Test {
    public static void main(String[] args) {
        System.out.printf("123");
    }

    @org.junit.Test
    public void test(){
        Assert.isTrue(new Boolean("true"));
    }
}
