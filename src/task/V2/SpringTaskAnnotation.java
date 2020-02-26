package task.V2;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * V2 ��ʱ���񣨲�ʵ�ֶ�̬���أ�
 * ����ע��Ķ�ʱ��
 */

@Component
public class SpringTaskAnnotation {
	
	//��ʱ ÿ1��ִ��һ��
	@Scheduled(cron="0/2 * * * * *")
	public void show(){
		System.out.println(new Date()+":Annotation run show method");
	}

	
	/**�������¡�����ʱִ��һ�� ֮��ÿ��2��ִ��һ��
	 * fixedRate ÿ�����ٺ���ִ��һ�θ÷��� ���ϴ�ִ��ʱ�俪ʼ����
	 */
	@Scheduled(fixedRate=1000*2)
	public void print(){
		System.out.println(new Date()+":Annotation run print method,");
	}

	/**
	 * �������ػ��� ����һ��ִ����Ϊ׼
	 * fixedDelay ��һ�η���ִ����� �ӳٶ��ٺ�����ִ�и÷���
	 */
	@Scheduled(fixedDelay=1*1000)
	public void init(){
		System.out.println(new Date()+":Annotation run init method,");
	}
	
}
