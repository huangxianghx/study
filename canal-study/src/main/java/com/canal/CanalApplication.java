package com.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 *
 */
@SpringBootApplication
public class CanalApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(CanalApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        //source.containsProperty("spring.profiles.active") 从JVM启动参数里面获取spring.profiles.active变量
        //System.getenv().containsKey("SPRING_PROFILES_ACTIVE") 从环境变量面获取SPRING_PROFILES_ACTIVE变量
        if (!source.containsProperty("spring.profiles.active")
                && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            // 如果未在命令行设置 --spring.profiles.active=dev 以及环境变量中没有
            // SPRING_PROFILES_ACTIVE
            app.setAdditionalProfiles("dev");// 默认使用开发环境
        }
        app.run(args);
    }
}
