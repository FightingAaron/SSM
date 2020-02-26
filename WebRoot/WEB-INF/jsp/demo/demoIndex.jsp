<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>demoIndex首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
		span{text-align:left;display:inline-block;width:100px;}
	</style>

  </head>
  
  <body>
    <form action="demo!insert.action">
    	<span>ID:</span><input name="id"><br>
    	<span>姓名：</span><input name="name"><br>
    	<span>性别：</span><input type="radio" name="sex" vaule="1">男<input type="radio" name="sex" value="2">女<br>
    	<span>年龄：</span><input name="age"><br>
    	<span>userId:</span><input name="userId"><br>
    	<span>userName:</span><input name="userName"><br>
    	<span>passWord:</span><input name="passWord"><br>
    	<input type="submit" value="确定">
    </form>
  </body>
</html>
