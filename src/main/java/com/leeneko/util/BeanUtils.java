package com.leeneko.util;

import org.springframework.context.ApplicationContext;

public class BeanUtils {
	
	public static Object getBean(Class<?> clazz) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		
		return applicationContext.getBean(clazz);
	}

}
