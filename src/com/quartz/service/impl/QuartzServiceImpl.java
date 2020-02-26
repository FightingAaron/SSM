package com.quartz.service.impl;

import java.util.List;


import com.quartz.bean.TaskBean;
import com.quartz.dao.QuartzDao;
import com.quartz.service.QuartzService;

public class QuartzServiceImpl implements QuartzService{

	private QuartzDao quartzDao;

	
	
	public QuartzDao getQuartzDao() {
		return quartzDao;
	}



	public void setQuartzDao(QuartzDao quartzDao) {
		this.quartzDao = quartzDao;
	}



	@Override
	public List<TaskBean> getAllTasks() throws Exception{
		return quartzDao.getAllTasks();
	}



	@Override
	public void addTask(TaskBean task) {
		quartzDao.addTask(task);
		
	}



	@Override
	public void updateTask(TaskBean task)  throws Exception{
		quartzDao.updateTask(task);
	}



	@Override
	public int deleteTask(String id) {
		return quartzDao.deleteTask(id);
	}



	@Override
	public TaskBean selectById(String id) {
		return quartzDao.selectById(id);
	}


	//根据name模糊查询
	@Override
	public List<TaskBean> selectByName(String name) {
		return quartzDao.selectByName(name);
	}
	
	
}
