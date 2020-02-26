package task.V2;

import java.util.Date;

/**
 * 基于xml配置定时任务
 */
public class SpringTaskXml {
	
	public void show(){
		System.out.println(new Date() +":XML is show run");
	}
	
	public void print(){
		System.out.println(new Date() +":XML is print run");
	}
}
