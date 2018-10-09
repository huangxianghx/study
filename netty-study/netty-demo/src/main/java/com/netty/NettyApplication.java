package com.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
public class NettyApplication {
    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(NettyApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        // 如果未在命令行设置 --spring.profiles.active=dev 以及环境变量中没有SPRING_PROFILES_ACTIVE
        if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles("dev");// 默认使用开发环境
        }
        app.run(args);
    }
}
