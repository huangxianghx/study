package com.hx.study.springbootstudy.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 注入enviroment
 *
 */
@Component
public class DIEnvironment {
    @Autowired
    Environment environment;

    public String getProValueFromEnvironment(String key) {
        return environment.getProperty(key);
    }

    /**
     * 获取当前生效环境
     * @return
     */
    public String getActiveEnvironment(){
        String[] activeProfiles = environment.getActiveProfiles();
        if(activeProfiles.length>0){
            return activeProfiles[0];
        }
        return "null";
    }
}
