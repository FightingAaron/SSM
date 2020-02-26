package task.V1.V11;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Job2 extends QuartzJobBean{

	private int timeout;
	private static int i=0;
	
	
	//调度工厂实例化后 经过timeout时间开始执行调度
	public void setTimeout(int timeout){
		this.timeout=timeout;
	}
	
	
	/**
	 * 要调度的具体任务
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new Date()+":V11 Job2定时任务执行中。。。");
		
	}

}
