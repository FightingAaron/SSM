package com.quartz.dao;

import java.util.List;

import com.quartz.bean.TaskBean;
import com.ssm1.bean.H_test;

public interface QuartzDao {
	public List<TaskBean> getAllTasks();//获取所有任务
	public void addTask(TaskBean task);
	public TaskBean selectById(String id);
	public List<TaskBean> selectByName(String name);
	public void updateTask(TaskBean task)  throws Exception;
	public int deleteTask(String id);
}
