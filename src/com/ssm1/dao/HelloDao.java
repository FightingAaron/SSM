package com.ssm1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssm1.bean.H_test;


public interface HelloDao {
	public void insertTest(H_test h_test);
	public H_test selectById(String id);
	public List<H_test> selectTests();
	public void updateTest(H_test h_test);
	public int deleteTest(String id);
}