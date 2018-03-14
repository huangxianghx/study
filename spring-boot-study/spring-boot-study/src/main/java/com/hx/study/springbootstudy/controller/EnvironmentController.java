package com.hx.study.springbootstudy.controller;

import com.hx.study.springbootstudy.config.PropertiesInfo;
import com.hx.study.springbootstudy.environment.DIEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class EnvironmentController {
    private Logger logger = LoggerFactory.getLogger(EnvironmentController.class);

    @Autowired
    DIEnvironment environment;
    @Autowired
    PropertiesInfo propertiesInfo;
    private static final ExecutorService exec = Executors.newSingleThreadExecutor();

    @RequestMapping(value = "/getEnvironment",method = RequestMethod.GET)
    public String getEnvironment(){
        String test = environment.getActiveEnvironment();
        String currentActiveEnvironment = propertiesInfo.getActiveProfile();
        logger.info("currentActiveEnvironment===>{}",currentActiveEnvironment);
        return test;
    }

    @RequestMapping(value = "/doDelete",method = RequestMethod.POST)
    public String doDelete(){
        try {
            exec.submit(new DoDelete());
            return "ok";
        } catch (Exception e) {
            logger.error("do delete超时", e);
        }
        return "no";
    }

    public class DoDelete implements Runnable{
        @Override
        public void run() {
            try{
                logger.info("do delete");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
