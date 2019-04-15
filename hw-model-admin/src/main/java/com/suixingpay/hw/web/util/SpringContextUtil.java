package com.suixingpay.hw.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SpringContextUtil
 * @Description java类作用描述
 * @Date 2019/4/11 15:09
 * @Created liuyan[liu_yan@suixingpay.com]
 */
@Configuration
@SuppressWarnings("all")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        applicationContext = contex;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    /**
     * 根据类从spring上下文获取bean。
     *
     * @param cls
     * @return
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }

    /**
     * 根据类名从spring上下文获取bean。
     *
     * @param cls
     * @return
     */
    public static <T> T getBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }

}
