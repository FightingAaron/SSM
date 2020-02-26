package com.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ��ȡ�����ļ��Ĺ����� ʵ�������ƹ���ģʽ�ķ���
 */
public class SpringUtil {
	//��������
	private static ApplicationContext context;
	
	//��ȡ�����ļ�
	static{
		context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("context�Ƿ�Ϊ�գ�"+context==null);
	}

	//��ȡbean
	public static Object getBean(String beanname){
		Object o=null;
		if(beanname!=null && !("").equals(o)){
			o=context.getBean(beanname);
		}
		return o;
	}
	
	
}
