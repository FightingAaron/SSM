<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>定时任务添加页面</title>
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
		span{text-align:left;display:inline-block;width:80px;padding:6px;}
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
    <h2>定时任务添加页面</h2>
    <form action="quartz!modifyTask.action" accept-charset="utf-8" method="post">
    	<input name="id" value='<s:property value="taskBean.id"/>' type="hidden">
    	<span>任务名称：</span><input name="name" value='<s:property value="taskBean.name"/>' type="text"><br>
    	<span>任务类型：</span>
    		<s:if test="taskBean!=null">
    			<s:if test="taskBean.type=='TIMING'"><!-- TIMING和INTERVAL -->
    				<input type="radio" name="type" value="TIMING" checked="checked">定时&nbsp;<input type="radio" name="type" value="INTERVAL">间隔
    			</s:if>
    			<s:else>
    				<input type="radio" name="type" value="TIMING" >定时&nbsp;<input type="radio" name="type" value="INTERVAL" checked="checked">间隔
    			</s:else>
    		</s:if>
    		<s:else>
    			<input type="radio" name="type" value="TIMING">定时&nbsp;<input type="radio" name="type" value="INTERVAL">间隔
    		</s:else><br>
    	<span>是否自启：</span>
    		<s:if test="taskBean!=null">
    			<s:if test="taskBean.ifAutoBoot=='Y'.toString()"><!-- Y和N -->
    				<input type="radio" name="ifAutoBoot" value="Y" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="ifAutoBoot" value="N">否
    			</s:if>
    			<s:else>
    				<input type="radio" name="ifAutoBoot" value="Y" >是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="ifAutoBoot" value="N" checked="checked">否
    			</s:else>
    		</s:if>
    		<s:else>
    			<input type="radio" name="ifAutoBoot" value="Y">是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="ifAutoBoot" value="N">否
    		</s:else><br>
    	<span>任务状态：</span>
    		<s:if test="taskBean!=null">
    			<s:if test="taskBean.status=='RUNNING'"><!-- PAUSE和RUNNING -->
    				<input type="radio" name="status" value="RUNNING" checked="checked">正常&nbsp;<input type="radio" name="status" value="PAUSE">暂停
    			</s:if>
    			<s:else>
    				<input type="radio" name="status" value="RUNNING" >正常&nbsp;<input type="radio" name="status" value="PAUSE" checked="checked">暂停
    			</s:else>
    		</s:if>
    		<s:else>
    			<input type="radio" name="status" value="RUNNING">正常&nbsp;<input type="radio" name="status" value="PAUSE">暂停
    		</s:else><br>
    	<span>任务类名：</span><input name="taskClass" value='<s:property value="taskBean.taskClass"/>'><br>
    	<span>执行策略：</span><input name="executionPolicy" value='<s:property value="taskBean.executionPolicy"/>'><br>
    	<span>任务描述：</span><input name="description" value='<s:property value="taskBean.description"/>'><br>
    	<input style="padding:2px;margin:5px;font-size: 17px;" type="submit" value="确定">
    </form>
  </body>
</html>