package com.quartz.service;

import java.util.List;

import com.quartz.bean.TaskBean;

public interface QuartzService {
	
	//��ȡ��������
	public List<TaskBean> getAllTasks() throws Exception;
	public void addTask(TaskBean task);
	public void updateTask(TaskBean task) throws Exception;
	public int deleteTask(String id);
	public TaskBean selectById(String id);
	public List<TaskBean> selectByName(String name);
	

}
