package com.ssm1.service;

import java.util.List;
import com.ssm1.bean.H_test;

public interface HelloService {
	public void insertTest(H_test h_test);
	public H_test selectById(String id);
	public List<H_test> selectTests();
	public void updateTest(H_test h_test);
	public int deleteTest(String id);

}
