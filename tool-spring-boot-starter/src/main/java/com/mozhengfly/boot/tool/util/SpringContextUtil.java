package com.mozhengfly.boot.tool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description SpringContextUtil
 * @Author mozhengfly
 * @Date 2019-07-01 21:54:49
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 判断当前上下文是否包含bean
     * @param name
     * @return
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name, 以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取所有的beans
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 获取所有的beans List
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeanListOfType(Class<T> clazz) {
        Map<String, T> beansOfType = getApplicationContext().getBeansOfType(clazz);
        if (MapUtils.isEmpty(beansOfType)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(beansOfType.values());
    }
}
