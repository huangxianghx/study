package com.hx.study.springbootstudy;

import com.hx.study.springbootstudy.listeners.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
public class SpringBootStudyApplication {

	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(SpringBootStudyApplication.class);
		//加载StartingEvent监听处理器
		app.addListeners(new ApplicationStartingEventListener());
		//加载EnvironmentPreparedEvent监听处理器
		app.addListeners(new ApplicationEnvironmentPreparedEventListener());
		//加载PreparedEvent监听处理器
		app.addListeners(new ApplicationPreparedEventListener());
		//加载ReadyEvent监听处理器
		app.addListeners(new ApplicationReadyEventListener());
		//加载FailedEvent监听处理器
		app.addListeners(new ApplicationFailedEventListener());

		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		// 如果未在命令行设置 --spring.profiles.active=dev 以及环境变量中没有SPRING_PROFILES_ACTIVE
		if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
			app.setAdditionalProfiles("prod");// 默认使用开发环境
		}
		app.run(args);
	}
}
