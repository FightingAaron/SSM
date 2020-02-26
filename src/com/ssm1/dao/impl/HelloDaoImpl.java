package com.ssm1.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm1.bean.H_test;
import com.ssm1.dao.HelloDao;


@Service
public class HelloDaoImpl implements HelloDao {
	
	private Logger logger=Logger.getLogger(HelloDaoImpl.class);
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	@Override
	public void insertTest(H_test h_test) {
		logger.info("进入到HelloDaoImpl中的insertTest方法...");
		try {
			sqlSession=sqlSessionFactory.openSession();
			sqlSession.insert("h_test.insertTest", h_test);
			sqlSession.commit();
			sqlSession.close();
 			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("h_test插入失败,"+e.getMessage());
		}
	}
	
	@Override
	public List<H_test> selectTests() {
		logger.info("进入到HelloDaoImpl中的selectTests方法...");
		List<H_test> list=null;
 		try {
			sqlSession=sqlSessionFactory.openSession();
			list=sqlSession.selectList("h_test.selectTest");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("h_test插入失败,"+e.getMessage());
		}
 		return list;
	}

	@Override
	public void updateTest(H_test h_test) {
		logger.info("进入到HelloDaoImpl中的updateTest方法...");
 		try {
			sqlSession=sqlSessionFactory.openSession();
			sqlSession.update("h_test.updateTest", h_test);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("h_test更新失败,"+e.getMessage());
		}
	}

	@Override
	public int deleteTest(String id) {
		logger.info("进入到HelloDaoImpl中的deleteTest方法...");
		int num=0;
 		try {
			sqlSession=sqlSessionFactory.openSession();
			sqlSession.delete("h_test.updateTest", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("h_test删除失败,"+e.getMessage());
		}
 		return num;
	}


	
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/* (non-Javadoc)
	 * @see com.ssm1.dao.HelloDao#selectById(java.lang.String)
	 */
	@Override
	public H_test selectById(String id) {
		logger.info("进入到HelloDaoImpl中的deleteTest方法...");
		H_test h_test=null;
 		try {
			sqlSession=sqlSessionFactory.openSession();
			h_test=sqlSession.selectOne("h_test.selectById", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("h_test根据ID查询失败,"+e.getMessage());
		}
		return h_test;
	}



	
	
}
