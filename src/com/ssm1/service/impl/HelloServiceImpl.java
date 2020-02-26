package com.ssm1.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssm1.bean.H_test;
import com.ssm1.dao.HelloDao;
import com.ssm1.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
	
	private HelloDao helloDao;
	@Override
	public void insertTest(H_test h_test) {
		helloDao.insertTest(h_test);

	}
	public HelloDao getHelloDao() {
		return helloDao;
	}
	public void setHelloDao(HelloDao helloDao) {
		this.helloDao = helloDao;
	}
	@Override
	public List<H_test> selectTests() {
		return helloDao.selectTests();
	}
	@Override
	public void updateTest(H_test h_test) {
		helloDao.updateTest(h_test);
		
	}
	@Override
	public int deleteTest(String id) {
		return helloDao.deleteTest(id);
	}
	/* (non-Javadoc)
	 * @see com.ssm1.service.HelloService#selectById(java.lang.String)
	 */
	@Override
	public H_test selectById(String id) {
		return helloDao.selectById(id);
	}
	
	
}
