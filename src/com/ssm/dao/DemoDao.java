package com.ssm.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DemoDao {
	public void insertTest(Map map);
	public void insertUser(Map map);
}
