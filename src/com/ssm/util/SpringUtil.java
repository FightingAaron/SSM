package com.ssm.util;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	//定义属性
	private static ApplicationContext context;
	
	//读取配置文件
	 static{
		 context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 System.out.println("context是否为空："+context==null);
	 }
	
	 //getbean方法
	 public static Object getBean(String beanname){
		 Object o=null;
		 if(beanname!=null&&!("").equals(beanname)){
			 o=context.getBean(beanname);
		 }
		 return o;
	 }
	 

	
	
	

}
