package task.V1.V11;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Job2 extends QuartzJobBean{

	private int timeout;
	private static int i=0;
	
	
	//���ȹ���ʵ������ ����timeoutʱ�俪ʼִ�е���
	public void setTimeout(int timeout){
		this.timeout=timeout;
	}
	
	
	/**
	 * Ҫ���ȵľ�������
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new Date()+":V11 Job2��ʱ����ִ���С�����");
		
	}

}
