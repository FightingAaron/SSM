<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>定时任务列表页</title>
    
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
        table { width: 800px; min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse;}
	</style>
	  
  	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){ 
		$("#searchByName").click(function() {
			//alert($("#taskName").val());
			var taskName=$("#taskName").val();
			/* $.ajax({
				url:'quartz.action',
				type:'post',
				dataType:'json',
				data:{taskName:taskName},
				success:function(result){
					window.location.reload();
				}
				
			}); */
			window.location.href="quartz.action?taskName="+taskName;
		});
	});
	
	//删除任务
	function del(){
		if($("input:checkbox:checked").size() == 0){
        	alert("至少选中一条记录");
 			return;
        }

		var ids="";
		$("input:checkbox:checked").each(function(){
			ids+=$(this).val();
			ids+=",";
		});
		
		$.ajax({
			type:'post',
			url:'SSM/quartz!deleteTask.action',
			//数据格式是json串，商品信息
			data:{ids:ids},
			dataType: "json",
			success:function(data){//返回json结果
				alert(data.desc);
				
			}
		});
		window.location.reload();
		
	};
	
	//开始任务
	function start(){
		/* if(!$("input:checkbox").prop("checked")){//判断是否有选中
			alert("至少选中一条记录");
			return;
		} */
		
		var isSelect="false";
		var checkArry = $("input:checkbox");
        for (var i = 0; i < checkArry.length; i++) { 
             if(checkArry[i].checked == true){
            	 isSelect="true";
            	 break;
             }
        }
         
        // 判断是否有勾选复选框 way1
        if(isSelect=="false"){
        	alert("至少选中一条记录");
 			return;
         }
        
        /* 判断是否有勾选复选框 way2
        if($("input:checkbox:checked").size() == 0){
        	alert("至少选中一条记录");
 			return;
        } */
		
		var ids="";
		$("input:checkbox:checked").each(function(){
			ids+=$(this).val();
			ids+=",";
		});
		
		$.ajax({
			type:'post',
			url:'SSM/quartz!startTask.action',
			//数据格式是json串，商品信息
			data:{startIds:ids},
			dataType: "json",
			success:function(data){//返回json结果
				alert(data.msg);
			}
		});
		window.location.reload();
		
	};
	
	//暂停任务
	function pause(){
		if($("input:checkbox:checked").size() == 0){
        	alert("至少选中一条记录");
 			return;
        }
		var ids="";
		$("input:checkbox:checked").each(function(){
			ids+=$(this).val();
			ids+=",";
		});
		$.ajax({
			type:'post',
			url:'SSM/quartz!pauseTask.action',
			//数据格式是json串，商品信息
			data:{pauseIds:ids},
			dataType: "json",
			success:function(data){//返回json结果
				alert(data.msg);
			}
		});
		window.location.reload();
		
		
	};
	</script>
	
  </head>
  
  <body>
  	<h2>定时任务列表</h2>
       <p style="width:800px;text-align: right;">
       	<input id="taskName" style="width:100px"/>
    	<a id="searchByName"><button style="margin-left: 5px">查询</button></a>
    	<a href="quartz!add.action?modifyType=add"><button style="margin-left: 5px">添加</button></a>
    	<button style="margin-left: 5px" onclick="del()">删除</button>
    	<button style="margin-left: 5px" onclick="start()">启动</button>
    	<button style="margin-left: 5px" onclick="pause()">暂停</button>
    </p>
    <table >
    	<tr>
    		<td></td>
    		<td>序号</td>
    		<td>任务名称</td>
    		<td>任务类型</td>
    		<td>是否自启</td>
    		<td>设定状态</td>
    		<td>实际状态</td>
    		<td>执行策略</td>
    		<td>描述</td>
    		<td>操作</td>
    	</tr>
    	<s:iterator id="task" value="tasks" status="count">
    		<tr>
    			<td><input type="checkbox" value='<s:property value="#task.id"/>'></td>
    			<td><s:property value="#count.count"/></td>
	    		<td><s:property value="#task.name"/></td>
	    		<td>
	    			<s:if test="#task.type=='TIMING'"><!-- TIMING和INTERVAL -->
	    				定时
	    			</s:if>
	    			<s:else>
	    				间隔
	    			</s:else>
	    		</td>
	    		<td>
	    			<s:if test='<s:property value="#task.ifAutoBoot"/>=="Y".toString()'><!-- TIMING和INTERVAL -->
	    				是
	    			</s:if>
	    			<s:else>
	    				否
	    			</s:else>
	    		</td>
	    		<td>
	    			<s:if test="#task.status=='NORMAL'"><!-- TIMING和INTERVAL -->
	    				正常
	    			</s:if>
	    			<s:else>
	    				暂停
	    			</s:else>
	    		</td>
	    		<td>
	    			<s:if test="#task.actualStatus=='PAUSED' || #task.actualStatus=='NONE'"><!-- TIMING和INTERVAL -->
	    				暂停
	    			</s:if>
	    			<s:elseif test="#task.actualStatus=='ERROR'"><!-- TIMING和INTERVAL -->
	    				异常
	    			</s:elseif>
	    			<s:elseif test="#task.actualStatus=='BLOCKED'"><!-- TIMING和INTERVAL -->
	    				阻塞
	    			</s:elseif>
	    			<s:else>
	    				正常
	    			</s:else>
	    		</td>
	    		<td><s:property value="#task.executionPolicy"/></td>
	    		<td><s:property value="#task.description"/></td>
	    		<!-- s:if标签的使用 %{#h_test.sex==1}或者  #h_test.sex==1 -->
	    		<td><a href='quartz!add.action?modifyType=update&id=<s:property value="#task.id"/>'>修改</a></td>
	    		<!-- <td><a href='quartz!delete.action?id=<s:property value="#task.id"/>'>删除</a></td> -->
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>