/**
 * 
 */
package com.quartz.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import task.V3.QuartzManager;

/**
 * @author ChenYongHeng
 * @version 2018年9月10日 下午5:19:27
 */
public class TestCase {
	public static void main(String[] args) {

		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/applicationContext-test.xml");
		try {
			Scheduler scheduler=(Scheduler) ctx.getBean("scheduler");
			
			Class<Job> taskClass = (Class<Job>) Class.forName("com.quartz.job.TestJob");
			//创建任务
			JobDetail job = newJob(taskClass).withIdentity("661c7e3fddf84cad8df1902f09a493f9", "default").build();
			//创建触发器
			CronTrigger trigger = newTrigger().withIdentity("661c7e3fddf84cad8df1902f09a493f9", "default").withSchedule(cronSchedule("*/5 * * * * ?"))
			        .build();
			scheduler.scheduleJob(job, trigger);
			System.out.println("【系统启动】开始（每1s输出一次）...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	
	}

}
