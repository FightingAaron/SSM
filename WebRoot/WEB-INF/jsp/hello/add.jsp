<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>HelloAction添加数据页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   	<%@ taglib prefix ="s" uri="/struts-tags"%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style>
		span{text-align:left;display:inline-block;width:60px;}
	</style>
  
  
  	<script type="text/javascript" src="js/jquery.js"></script>

	<script type="text/javascript">
	$(function () { 
		//alert($("#test").val());
		//alert("id");
	});
	</script>
  
  </head>
  
  <body>
    <form action="hello!insert.action">
    	<span>ID:</span><input name="id" value='<s:property value="shownTest.id"/>' type="text"><br>
    	<span>姓名：</span><input name="name" value='<s:property value="shownTest.name"/>'><br>
    	<span>性别：</span>
    		<s:if test="shownTest.sex!=null">
    			<s:if test="shownTest.sex==1">
    				<input type="radio" name="sex" value="1" checked="checked">男<input type="radio" name="sex" value="2">女
    			</s:if>
    			<s:else>
    				<input type="radio" name="sex" value="1" >男<input type="radio" name="sex" value="2" checked="checked">女
    			</s:else>
    		</s:if>
    		<s:else>
    			<input type="radio" name="sex" value="1">男<input type="radio" name="sex" value="2">女
    		</s:else><br>
    	<span>年龄：</span><input name="age" value='<s:property value="shownTest.age"/>'><br>
    	<input type="submit" value="确定">
    </form>
  </body>
</html>
