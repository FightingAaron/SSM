package com.ssm.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.ssm.service.DemoService;


public class DemoAction extends ActionSupport implements ServletResponseAware{
	
	
	private static final Logger logger=Logger.getLogger(DemoAction.class);
	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
	
	@Autowired//service层的注入
	private DemoService demoService;
	private String id;
	private String name;
	private String sex;
	private String age;
	
	private String userId;
	private String userName;
	private String passWord;
	
	
	public String execute(){
		logger.info("进入DemoAction的index方法。。。");
		return "index";
	}
	
	public void insert() throws Exception{
		logger.info("进入DemoAction的insert方法。。。");
		
		try {
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("id", id);
			map1.put("name", name);
			map1.put("sex",sex);
			map1.put("age",age);
			
			//插入user表
			Map<String,Object> map2=new HashMap<String,Object>();
			map2.put("userId", userId);
			map2.put("userName", userName);
			map2.put("passWord", passWord);
			
			demoService.insertTestTra(map1, map2);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			pw.print("插入数据成功：map1="+map1+",map2="+map2);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	
	public String getUserName() {
		return userName;
	}

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
