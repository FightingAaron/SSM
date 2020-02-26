<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path=request.getScheme();//通讯的协议
	String basePath=path+"://"+request.getServerName()+"/"+request.getServerPort();

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>hellIndex页面</title>
</head>
<body>
	<p>进入helloIndex页面</p>
</body>
</html>