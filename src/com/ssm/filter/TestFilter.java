package com.ssm.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


//拦截器 test
public class TestFilter implements Filter{

	public static final Logger logger=Logger.getLogger(TestFilter.class);
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		request=(HttpServletRequest)req;
		response=(HttpServletResponse)res;
		String url=request.getRequestURL().toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("请求url："+url+",请求时间："+sdf.format(new Date()));
		logger.info("请求url："+url+",请求时间："+sdf.format(new Date()));
		if(url.contains("doFilterTest.jsp")){
			response.sendRedirect("https://www.baidu.com/");
		}else{
			//放行
			 chain.doFilter(request, response);
			 return;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//获取初始化参数 定义在web.xml里的<init-param></init-param/>
		logger.info("进去到TestFilter的init方法。。。");
		logger.info("过滤器名字："+config.getFilterName());
		logger.info("web.xml中filter_value为："+config.getInitParameter("filter_value"));
	}

}
