package com.lin.infrastructure.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author linzihao
 */
@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringUtils() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	public static <T> T getBean(String beanName) {
		if (applicationContext.containsBean(beanName)) {
			return (T) applicationContext.getBean(beanName);
		} else {
			return null;
		}
	}

	public static <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> baseType) {
		return applicationContext.getBeansOfType(baseType);
	}

}
