package com.ssm1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.ssm.service.DemoService;
import com.ssm1.bean.H_test;
import com.ssm1.service.HelloService;

@Controller
@Scope("protptype")
public class HelloAction extends ActionSupport implements ServletResponseAware{
	
	private static final Logger logger=Logger.getLogger(HelloAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}


	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	private HelloService helloService;
	private String id;
	private String name;
	private String sex;
	private String age;
	
	
	private List<H_test> list;
	private H_test shownTest;
	private String modifyType;
	
	
	
	public String execute(){
		logger.info("���뵽HelloAction��index����������");
		list=helloService.selectTests();
		System.out.println(list.toString());
		return "index";
	}
		
	
	//���ҳ��
	public String add(){
		logger.info("���뵽HelloAction��add����������");
		try {
			if("update".equals(modifyType)){//��������
				shownTest=helloService.selectById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	public String insert() throws IOException{
		logger.info("���뵽HelloAction��insert����������");
		try {
			/*Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("sex",sex);
			map.put("age",age);*/
			H_test h_test=new H_test();
			h_test.setId(id);
			h_test.setName(name);
			h_test.setSex(sex);
			h_test.setAge(age);
			//����ж� �������������� �����������
			if(helloService.selectById(id)==null){//���²���
				helloService.insertTest(h_test);
			}else{//�������
				helloService.updateTest(h_test);
			}
			
			/*System.out.println("DemoAction��insert����������µ����ݣ�"+h_test.toString());
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			pw.print("����������ݳɹ���"+h_test.toString());
			pw.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update";
	}
	
	public String delete(){
		logger.info("���뵽HelloAction��delete����������");
		try {
			helloService.deleteTest(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
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
	public void setAge(String age) {
		this.age = age;
	}

	
	/**
	 * @return the list
	 */
	public List<H_test> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<H_test> list) {
		this.list = list;
	}

	
	/**
	 * @return the shownTest
	 */
	public H_test getShownTest() {
		return shownTest;
	}

	/**
	 * @param shownTest the shownTest to set
	 */
	public void setShownTest(H_test shownTest) {
		this.shownTest = shownTest;
	}

	public HelloService getHelloService() {
		return helloService;
	}

	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}

	/**
	 * @return the modifyType
	 */
	public String getModifyType() {
		return modifyType;
	}

	/**
	 * @param modifyType the modifyType to set
	 */
	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}
	
	
}
