package com.hx.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * spring工具类
 *
 * @author xianyongjie
 */
public  class SpringUtil implements ApplicationContextAware {

    /**
     * applicationContext
     */
    public static ApplicationContext  applicationContext ;


    /**
     * 获取实例
     *
     * @param name Bean名称
     * @return 实例
     */
    public static Object getBean(String name) {
        Assert.hasText(name);
        return applicationContext.getBean(name);
    }
    
    /**
     * 获取实例
     *
     * @param type
     * @return 实例
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
    
    
    /**
	 * 获取实例
	 * 
	 * @param type
	 * @return 实例
	 */
	public static <T> T getBean(Class<T> type, Object... args) {
		return applicationContext.getBean(type, args);
	}

    /**
     * 获取实例
     *
     * @param name Bean名称
     * @param type Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name);
        Assert.notNull(type);
        return applicationContext.getBean(name, type);
    }

    /**
     * 获取国际化消息
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    public static String getMessage(String code, Object... args) {
        LocaleResolver localeResolver = getBean("localeResolver",
                LocaleResolver.class);
        Locale locale = localeResolver.resolveLocale(null);
        return applicationContext.getMessage(code, args, locale);
    }


    /**
     * 检测类是否存在
     *
     * @param classPath
     * @return
     */
    public static boolean isClassExist(String classPath) {
        try {
            Class aClass = Class
                    .forName(classPath);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}