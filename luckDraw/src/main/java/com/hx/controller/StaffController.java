package com.hx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    /**
     * 人员列表展示
     */
    @RequestMapping(value = "/staffList" ,method = RequestMethod.POST)
    public String staffList() {
        return null;
    }
}
