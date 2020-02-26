package com.ssm.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport implements ServletResponseAware {

	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public String execute(){
		System.out.println("����HelloAction��execute����");
		return "success";
	}
	
	public void say() throws Exception{
		System.out.println("����HelloAction��say����");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("yeah,i'm winner");
		pw.close();
		
	}
}
