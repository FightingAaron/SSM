package task.V3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("task/V3/spring-quartz-V31.xml");
		QuartzManager quartzManager=(QuartzManager) ctx.getBean("quartzManager");
		try {
			 System.out.println("【系统启动】开始(每1秒输出一次 job2)..."); 
			 
			 
			 Thread.sleep(5000);
			 System.out.println("【增加job1启动】开始（每1秒输出一次）...");
			 quartzManager.addJob("jobName1", "group", "trigger", "triggerGroup", MyJob.class, "0/1 * * * * ?");
			 
			 
	         Thread.sleep(5000);    
	         System.out.println("【修改job1时间】开始(每5s输出一次)...");    
	         quartzManager.modifyJobTime("jobName1", "group", "trigger", "triggerGroup", "0/5 *  * * * ?"); 
	         
	         
	         Thread.sleep(10000);    
             System.out.println("【移除job1定时】开始...");    
             quartzManager.removeJob("jobName1", "group", "trigger", "triggerGroup");
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
