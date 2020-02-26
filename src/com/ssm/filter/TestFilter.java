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


//������ test
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
		System.out.println("����url��"+url+",����ʱ�䣺"+sdf.format(new Date()));
		logger.info("����url��"+url+",����ʱ�䣺"+sdf.format(new Date()));
		if(url.contains("doFilterTest.jsp")){
			response.sendRedirect("https://www.baidu.com/");
		}else{
			//����
			 chain.doFilter(request, response);
			 return;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//��ȡ��ʼ������ ������web.xml���<init-param></init-param/>
		logger.info("��ȥ��TestFilter��init����������");
		logger.info("���������֣�"+config.getFilterName());
		logger.info("web.xml��filter_valueΪ��"+config.getInitParameter("filter_value"));
	}

}
