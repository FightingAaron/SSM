package task.V3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("task/V3/spring-quartz-V31.xml");
		QuartzManager quartzManager=(QuartzManager) ctx.getBean("quartzManager");
		try {
			System.out.println("【系统启动】开始（每1s输出一次）...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
