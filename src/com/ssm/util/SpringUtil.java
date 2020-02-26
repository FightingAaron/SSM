package com.ssm.util;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	//��������
	private static ApplicationContext context;
	
	//��ȡ�����ļ�
	 static{
		 context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 System.out.println("context�Ƿ�Ϊ�գ�"+context==null);
	 }
	
	 //getbean����
	 public static Object getBean(String beanname){
		 Object o=null;
		 if(beanname!=null&&!("").equals(beanname)){
			 o=context.getBean(beanname);
		 }
		 return o;
	 }
	 

	
	
	

}
