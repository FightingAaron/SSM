<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>HelloAction首页</title>
    
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
		table,table tr th, table tr td { border:1px solid #0094ff; }
        table { width: 280px; min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse;}
	</style>
  </head>
  
  <body>
  	<p>h_test表格数据</p>
    <p style="width:280px;text-align: right;"><a href="hello!add.action?modifyType=add"><button>添加数据</button></a></p>
    <table >
    	<tr>
    		<td>ID</td>
    		<td>NAME</td>
    		<td>AGE</td>
    		<td>SEX</td>
    		<td colspan="2" width="30px">操作</td>
    	</tr>
    	<s:iterator id="h_test" value="list">
    		<tr>
	    		<td><s:property value="#h_test.id"/></td>
	    		<td><s:property value="#h_test.name"/></td>
	    		<td><s:property value="#h_test.age"/></td>
	    		<!-- s:if标签的使用 %{#h_test.sex==1}或者  #h_test.sex==1 -->
	    		<td><s:if test="%{#h_test.sex==1}">男</s:if><s:else>女</s:else></td>
	    		<td><a href='hello!add.action?modifyType=update&id=<s:property value="#h_test.id"/>'>编辑</a></td>
	    		<td><a href='hello!delete.action?id=<s:property value="#h_test.id"/>'>删除</a></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
