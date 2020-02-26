package com.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 读取配置文件的工具类 实现了类似工厂模式的方法
 */
public class SpringUtil {
	//定义属性
	private static ApplicationContext context;
	
	//读取配置文件
	static{
		context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("context是否为空："+context==null);
	}

	//读取bean
	public static Object getBean(String beanname){
		Object o=null;
		if(beanname!=null && !("").equals(o)){
			o=context.getBean(beanname);
		}
		return o;
	}
	
	
}
