package task.V2;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * V2 定时任务（不实现动态加载）
 * 基于注解的定时器
 */

@Component
public class SpringTaskAnnotation {
	
	//定时 每1秒执行一次
	@Scheduled(cron="0/2 * * * * *")
	public void show(){
		System.out.println(new Date()+":Annotation run show method");
	}

	
	/**心跳更新。启动时执行一次 之后每隔2秒执行一次
	 * fixedRate 每隔多少毫秒执行一次该方法 以上次执行时间开始计算
	 */
	@Scheduled(fixedRate=1000*2)
	public void print(){
		System.out.println(new Date()+":Annotation run print method,");
	}

	/**
	 * 启动加载缓存 以上一次执行完为准
	 * fixedDelay 当一次方法执行完毕 延迟多少毫秒再执行该方法
	 */
	@Scheduled(fixedDelay=1*1000)
	public void init(){
		System.out.println(new Date()+":Annotation run init method,");
	}
	
}
