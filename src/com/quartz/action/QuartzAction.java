package com.quartz.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.quartz.bean.TaskBean;
import com.quartz.constant.Constant;
import com.quartz.service.QuartzService;
import com.util.StringUtil;

import task.V4.ITaskManager;



/** 
 * quartz定时任务
 * 根据数据库动态加载
 */
@Controller
@RequestMapping("task")
public class QuartzAction {
	
	private Logger logger=Logger.getLogger(QuartzAction.class);
	
	private QuartzService quartzService;	
	private ITaskManager taskManager;
	private String name;
	private String type;
	private String status;
	private String ifAutoBoot;
	private String executionPolicy;
	private String taskClass;
	private String description;
	private String operator;
	
	
	private String modifyType;
	private TaskBean taskBean;
	
	private List<TaskBean> tasks;

	/**
	 *  定时任务首页 显示本任务列表
	 */
	public String execute(){
		logger.info("进入到QuartzAction中execute方法...");
		tasks=new ArrayList<TaskBean>();
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String taskName=request.getParameter("taskName");
			if(taskName==null || "".equals(taskName)){//所有任务
				tasks=quartzService.getAllTasks();
			}else{//根据输入串模糊查询搜索
				taskName= new String(request.getParameter("taskName").getBytes("ISO8859-1"),"utf-8");
				tasks=quartzService.selectByName(taskName);
			}
			//将 定时任务实际状态写入列表中
			for(TaskBean task:tasks){
				System.out.println(getActualStatus(task));
				task.setActualStatus(getActualStatus(task));
				//task.setActualStatus("ERROR");
			}
		} catch (Exception e) {
			logger.info("定时任务列表查询失败...");
			e.printStackTrace();
		}
		System.out.println(tasks.toString());
		return "index";
	}
	


	//定时任务暂停    ajax请求
	public void pauseTask() throws IOException{
		logger.info("进入到QuartzAction中pause方法...");
		String pauseIds=null;
		String failIds="";//失败的ID
		JSONObject msg = new JSONObject();
		String state="0";//成功
		String desc="操作成功";
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		try {
			pauseIds=request.getParameter("pauseIds");
			String[] ids=pauseIds.split(",");
			for(String pauseId:ids){
				String status=taskManager.getJobStatusByName(pauseId, Constant.TASK_DEFAULT_GROUP_NAME);
				//返回信息
				if(!"NONE".equals(status) ) {
//					msg.put(Constant.STATE, "-1");
//					msg.put(Constant.MSG, "当前任务未执行，无需暂停！");
//				}else {
					boolean flag = taskManager.deleteJob(pauseId,Constant.TASK_DEFAULT_GROUP_NAME);
					if(!flag) {
//						msg.put(Constant.STATE, "0");
//						msg.put(Constant.MSG, "停止任务成功！");
//					}else {
						state="-1";
						failIds+=pauseId+",";
					}
				}
			}
			if(failIds==null || "".equals(failIds)){//操作成功
				desc="操作成功";
			}else{
				desc="操作失败,失败ID有"+failIds+",请联系运维查看详情";
			}
		} catch (Exception e) {
			state="-1";
			desc=e.getMessage();
			logger.info("ID为"+failIds+"的任务暂停失败...");
			e.printStackTrace();
			
		}
		msg.put(Constant.STATE, state);
		msg.put(Constant.MSG, desc);
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(msg.toString());
		
	}
	
	
	//启动任务  ajax 
	public void startTask() throws IOException{
		logger.info("进入到QuartzAction中start方法...");
		JSONObject msg = null;
		String state="0";//0代表成功  -1失败
		String desc="操作成功";
		String failIds="";
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//获取当前任务的状态
			String startIds=request.getParameter("startIds");
			String[] ids=startIds.split(",");
			for(String startId:ids){
				status = taskManager.getJobStatusByName(startId, Constant.TASK_DEFAULT_GROUP_NAME);
				//当任务状态为停止，不存在，完成时方可启动任务
				if("PAUSED".equals(status) || "NONE".equals(status) ) {
					TaskBean task = quartzService.selectById(startId);
					String taskClass = task.getTaskClass();
					String TaskExecutionPolicy = task.getExecutionPolicy();
					//启动任务
					JSONObject returnJson = taskManager.schedule(taskClass, startId, Constant.TASK_DEFAULT_GROUP_NAME, TaskExecutionPolicy);
					if("-1".equals(returnJson.getString("state"))){
						state="-1";
						failIds+=startId+":"+returnJson.getString("msg")+",";
					};
					
				}/*else {
					msg = new JSONObject();
					msg.put(Constant.STATE, "-1");
					msg.put(Constant.MSG, "当前任务已启动，无需再次启动！");
				}*/
				
			}
			
			if(failIds==null || "".equals(failIds)){//操作成功
				desc="操作成功";
			}else{
				desc="操作失败,失败ID有"+failIds.substring(0,failIds.length()-1)+",请联系运维查看详情";
			}
		} catch (Exception e) {
			state="-1";
			desc=e.getMessage();
			logger.info("任务启动失败...");
			e.printStackTrace();
		}
		
		msg = new JSONObject();
		msg.put(Constant.STATE, state);
		msg.put(Constant.MSG, desc);
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(msg.toString());
	}
	
	
	
	//添加页面
	public String add(){
		logger.info("进入到HelloAction的add方法。。。");
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		String modifyType=request.getParameter("modifyType");
		try {
			if("update".equals(modifyType)){//更新数据
				taskBean=quartzService.selectById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	
	public String modifyTask(){
		logger.info("进入到QuartzAction中modifyTask方法...");
		String modifyId="";
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			//HttpServletResponse response=ServletActionContext.getResponse();
			modifyId=request.getParameter("id");
			TaskBean task=new TaskBean();
			task.setName(name);
			task.setType(type);
			task.setStatus(status);
			task.setIfAutoBoot(ifAutoBoot);
			task.setExecutionPolicy(executionPolicy);
			task.setTaskClass(taskClass);
			task.setDescription(description);
			task.setUpdateDate(new Date());
			task.setOperator("1");
			if(modifyId==null||"".equals(modifyId)){//添加
				String id=StringUtil.getUUID32();
				task.setId(id);
				task.setCreateDate(new Date());
				quartzService.addTask(task);
			}else{//更新
				task.setId(modifyId);
				quartzService.updateTask(task);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("插入更新数据失败,"+e.getMessage());
		}
		return "modify";
	}
	
	
	
	
	
	public void deleteTask() throws IOException{
		logger.info("进入到QuartzAction中deleteTask方法...");
		String desc="操作成功";//描述失败的任务
		String state="0";//0 操作成功 -1 操作失败
		String failIds="";
		HttpServletResponse response=ServletActionContext.getResponse();
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String ids=request.getParameter("ids");
			String[] allIds=ids.split(",");
			for(String id:allIds){
				String status = taskManager.getJobStatusByName(id, Constant.TASK_DEFAULT_GROUP_NAME);
				if(!"NONE".equals(status) ) {	
					boolean flag=taskManager.deleteJob(id,Constant.TASK_DEFAULT_GROUP_NAME);
					if(flag){//删除成功
						quartzService.deleteTask(id);
					}else{
						failIds+=id+",";
					}
				}
			}
			if(failIds==null || "".equals(failIds)){//操作成功
				desc="操作成功";
			}else{
				desc="操作失败,失败ID有"+failIds+",请联系运维查看详情";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("task删除失败,"+e.getMessage());
			state="-1";
			desc=e.getMessage();
		}
		
		JSONObject tempJson = new JSONObject();
		tempJson.put("state",state);
    	tempJson.put("desc", desc);
    	response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(tempJson.toString());
		//return "delete";
	}
	
	//查询定时任务实际状态
	public String getActualStatus(TaskBean task) throws Exception{
		return taskManager.getJobStatusByName(task.getId(), Constant.TASK_DEFAULT_GROUP_NAME);
	}
	
	public String getNameById(String id){
		logger.info("根据ID查询任务名称...");
		String jobName="";
		TaskBean task=quartzService.selectById(id);
		jobName=task.getName();
		return jobName;
	}
	

	public QuartzService getQuartzService() {
		return quartzService;
	}

	public void setQuartzService(QuartzService quartzService) {
		this.quartzService = quartzService;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getIfAutoBoot() {
		return ifAutoBoot;
	}


	public void setIfAutoBoot(String ifAutoBoot) {
		this.ifAutoBoot = ifAutoBoot;
	}



	public String getExecutionPolicy() {
		return executionPolicy;
	}



	public void setExecutionPolicy(String executionPolicy) {
		this.executionPolicy = executionPolicy;
	}



	public String getTaskClass() {
		return taskClass;
	}


	public void setTaskClass(String taskClass) {
		this.taskClass = taskClass;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String getModifyType() {
		return modifyType;
	}


	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}


	public List<TaskBean> getTasks() {
		return tasks;
	}


	public void setTasks(List<TaskBean> tasks) {
		this.tasks = tasks;
	}


	public ITaskManager getTaskManager() {
		return taskManager;
	}


	public void setTaskManager(ITaskManager taskManager) {
		this.taskManager = taskManager;
	}


	public TaskBean getTaskBean() {
		return taskBean;
	}


	public void setTaskBean(TaskBean taskBean) {
		this.taskBean = taskBean;
	}


	
}
