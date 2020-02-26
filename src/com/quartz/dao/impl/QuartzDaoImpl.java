package com.quartz.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.quartz.bean.TaskBean;
import com.quartz.dao.QuartzDao;
import com.ssm1.bean.H_test;
import com.ssm1.dao.impl.HelloDaoImpl;

public class QuartzDaoImpl implements QuartzDao{

	
	private Logger logger=Logger.getLogger(HelloDaoImpl.class);
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	@Override
	public List<TaskBean> getAllTasks() {
		logger.info("���뵽QuartzDaoImpl�е�getAllTasks����...");
		List<TaskBean> list=null;
 		try {
			sqlSession=sqlSessionFactory.openSession();
			list=sqlSession.selectList("quartz.getAllTasks");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task��ȡȫ������ʧ��,"+e.getMessage());
		}
 		return list;
	}
	@Override
	public void addTask(TaskBean task) {
		logger.info("���뵽QuartzDaoImpl�е�addTask����...");
		try {
			sqlSession=sqlSessionFactory.openSession();
			sqlSession.insert("quartz.addTask", task);
			sqlSession.commit();
			sqlSession.close();
 			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task����ʧ��,"+e.getMessage());
		}
	
		
	}
	@Override
	public TaskBean selectById(String id) {
		logger.info("���뵽QuartzDaoImpl�е�selectById����...");
		TaskBean task=null;
		try {
			sqlSession=sqlSessionFactory.openSession();
			task=sqlSession.selectOne("quartz.selectById", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task����ID��ѯʧ��,"+e.getMessage());

		}
		return task;
	}
	@Override
	public void updateTask(TaskBean task)  throws Exception{
		logger.info("���뵽QuartzDaoImpl�е�updateTask����...");
		sqlSession=sqlSessionFactory.openSession();
		sqlSession.update("quartz.updateTask", task);
		
	}
	@Override
	public int deleteTask(String id) {
		logger.info("���뵽QuartzDaoImpl�е�deleteTask����...");
		int num=0;
		try {
			sqlSession=sqlSessionFactory.openSession();
			num=sqlSession.delete("quartz.deleteTask", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task����ʧ��,"+e.getMessage());
		}
		return num;
	}
	
	@Override
	public List<TaskBean> selectByName(String name) {
		logger.info("���뵽QuartzDaoImpl�е�selectByName����...");
		List<TaskBean> tasks=null;
		try {
			sqlSession=sqlSessionFactory.openSession();
			tasks=sqlSession.selectList("quartz.selectByName", name);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task��ѯʧ��,"+e.getMessage());
		}
		return tasks;
	}
	
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	

}
