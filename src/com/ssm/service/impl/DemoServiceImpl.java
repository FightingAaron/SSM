package com.ssm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.DemoDao;
import com.ssm.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired//◊¢»Î
	private DemoDao demoDao;
	@Override
	public void insertTest(Map map) {
		demoDao.insertTest(map);

	}
	@Override
	public void insertUser(Map map) {
		demoDao.insertUser(map);
		
	}
	
	//≤‚ ‘ ¬ŒÒ
	public void insertTestTra(Map map1,Map map2) {
		demoDao.insertTest(map1);
		demoDao.insertUser(map2);
	}
}
