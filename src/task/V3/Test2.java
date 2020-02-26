package task.V3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("task/V3/spring-quartz-V31.xml");
		QuartzManager quartzManager=(QuartzManager) ctx.getBean("quartzManager");
		try {
			 System.out.println("��ϵͳ��������ʼ(ÿ1�����һ�� job2)..."); 
			 
			 
			 Thread.sleep(5000);
			 System.out.println("������job1��������ʼ��ÿ1�����һ�Σ�...");
			 quartzManager.addJob("jobName1", "group", "trigger", "triggerGroup", MyJob.class, "0/1 * * * * ?");
			 
			 
	         Thread.sleep(5000);    
	         System.out.println("���޸�job1ʱ�䡿��ʼ(ÿ5s���һ��)...");    
	         quartzManager.modifyJobTime("jobName1", "group", "trigger", "triggerGroup", "0/5 *  * * * ?"); 
	         
	         
	         Thread.sleep(10000);    
             System.out.println("���Ƴ�job1��ʱ����ʼ...");    
             quartzManager.removeJob("jobName1", "group", "trigger", "triggerGroup");
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
