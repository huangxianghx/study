package com.hx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrizeController {
    /**
     * 奖品列表展示
     */
    @RequestMapping(value = "/prizeList" ,method = RequestMethod.POST)
    public String prizeList() {
        return null;
    }
}
