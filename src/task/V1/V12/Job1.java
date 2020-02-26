package task.V1.V12;

import java.util.Date;

/*
 * 不继承特定基类
 */
public class Job1 {
	public void doJob1(){
		System.out.println(new Date()+":Job1-不继承QuartzJobBean方式-调度进行中。。。");
	}
}
