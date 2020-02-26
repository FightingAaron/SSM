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
 * quartz��ʱ����
 * �������ݿ⶯̬����
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
	 *  ��ʱ������ҳ ��ʾ�������б�
	 */
	public String execute(){
		logger.info("���뵽QuartzAction��execute����...");
		tasks=new ArrayList<TaskBean>();
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String taskName=request.getParameter("taskName");
			if(taskName==null || "".equals(taskName)){//��������
				tasks=quartzService.getAllTasks();
			}else{//�������봮ģ����ѯ����
				taskName= new String(request.getParameter("taskName").getBytes("ISO8859-1"),"utf-8");
				tasks=quartzService.selectByName(taskName);
			}
			//�� ��ʱ����ʵ��״̬д���б���
			for(TaskBean task:tasks){
				System.out.println(getActualStatus(task));
				task.setActualStatus(getActualStatus(task));
				//task.setActualStatus("ERROR");
			}
		} catch (Exception e) {
			logger.info("��ʱ�����б��ѯʧ��...");
			e.printStackTrace();
		}
		System.out.println(tasks.toString());
		return "index";
	}
	


	//��ʱ������ͣ    ajax����
	public void pauseTask() throws IOException{
		logger.info("���뵽QuartzAction��pause����...");
		String pauseIds=null;
		String failIds="";//ʧ�ܵ�ID
		JSONObject msg = new JSONObject();
		String state="0";//�ɹ�
		String desc="�����ɹ�";
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		try {
			pauseIds=request.getParameter("pauseIds");
			String[] ids=pauseIds.split(",");
			for(String pauseId:ids){
				String status=taskManager.getJobStatusByName(pauseId, Constant.TASK_DEFAULT_GROUP_NAME);
				//������Ϣ
				if(!"NONE".equals(status) ) {
//					msg.put(Constant.STATE, "-1");
//					msg.put(Constant.MSG, "��ǰ����δִ�У�������ͣ��");
//				}else {
					boolean flag = taskManager.deleteJob(pauseId,Constant.TASK_DEFAULT_GROUP_NAME);
					if(!flag) {
//						msg.put(Constant.STATE, "0");
//						msg.put(Constant.MSG, "ֹͣ����ɹ���");
//					}else {
						state="-1";
						failIds+=pauseId+",";
					}
				}
			}
			if(failIds==null || "".equals(failIds)){//�����ɹ�
				desc="�����ɹ�";
			}else{
				desc="����ʧ��,ʧ��ID��"+failIds+",����ϵ��ά�鿴����";
			}
		} catch (Exception e) {
			state="-1";
			desc=e.getMessage();
			logger.info("IDΪ"+failIds+"��������ͣʧ��...");
			e.printStackTrace();
			
		}
		msg.put(Constant.STATE, state);
		msg.put(Constant.MSG, desc);
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(msg.toString());
		
	}
	
	
	//��������  ajax 
	public void startTask() throws IOException{
		logger.info("���뵽QuartzAction��start����...");
		JSONObject msg = null;
		String state="0";//0����ɹ�  -1ʧ��
		String desc="�����ɹ�";
		String failIds="";
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//��ȡ��ǰ�����״̬
			String startIds=request.getParameter("startIds");
			String[] ids=startIds.split(",");
			for(String startId:ids){
				status = taskManager.getJobStatusByName(startId, Constant.TASK_DEFAULT_GROUP_NAME);
				//������״̬Ϊֹͣ�������ڣ����ʱ������������
				if("PAUSED".equals(status) || "NONE".equals(status) ) {
					TaskBean task = quartzService.selectById(startId);
					String taskClass = task.getTaskClass();
					String TaskExecutionPolicy = task.getExecutionPolicy();
					//��������
					JSONObject returnJson = taskManager.schedule(taskClass, startId, Constant.TASK_DEFAULT_GROUP_NAME, TaskExecutionPolicy);
					if("-1".equals(returnJson.getString("state"))){
						state="-1";
						failIds+=startId+":"+returnJson.getString("msg")+",";
					};
					
				}/*else {
					msg = new JSONObject();
					msg.put(Constant.STATE, "-1");
					msg.put(Constant.MSG, "��ǰ�����������������ٴ�������");
				}*/
				
			}
			
			if(failIds==null || "".equals(failIds)){//�����ɹ�
				desc="�����ɹ�";
			}else{
				desc="����ʧ��,ʧ��ID��"+failIds.substring(0,failIds.length()-1)+",����ϵ��ά�鿴����";
			}
		} catch (Exception e) {
			state="-1";
			desc=e.getMessage();
			logger.info("��������ʧ��...");
			e.printStackTrace();
		}
		
		msg = new JSONObject();
		msg.put(Constant.STATE, state);
		msg.put(Constant.MSG, desc);
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(msg.toString());
	}
	
	
	
	//���ҳ��
	public String add(){
		logger.info("���뵽HelloAction��add����������");
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		String modifyType=request.getParameter("modifyType");
		try {
			if("update".equals(modifyType)){//��������
				taskBean=quartzService.selectById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	
	public String modifyTask(){
		logger.info("���뵽QuartzAction��modifyTask����...");
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
			if(modifyId==null||"".equals(modifyId)){//���
				String id=StringUtil.getUUID32();
				task.setId(id);
				task.setCreateDate(new Date());
				quartzService.addTask(task);
			}else{//����
				task.setId(modifyId);
				quartzService.updateTask(task);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("�����������ʧ��,"+e.getMessage());
		}
		return "modify";
	}
	
	
	
	
	
	public void deleteTask() throws IOException{
		logger.info("���뵽QuartzAction��deleteTask����...");
		String desc="�����ɹ�";//����ʧ�ܵ�����
		String state="0";//0 �����ɹ� -1 ����ʧ��
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
					if(flag){//ɾ���ɹ�
						quartzService.deleteTask(id);
					}else{
						failIds+=id+",";
					}
				}
			}
			if(failIds==null || "".equals(failIds)){//�����ɹ�
				desc="�����ɹ�";
			}else{
				desc="����ʧ��,ʧ��ID��"+failIds+",����ϵ��ά�鿴����";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("taskɾ��ʧ��,"+e.getMessage());
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
	
	//��ѯ��ʱ����ʵ��״̬
	public String getActualStatus(TaskBean task) throws Exception{
		return taskManager.getJobStatusByName(task.getId(), Constant.TASK_DEFAULT_GROUP_NAME);
	}
	
	public String getNameById(String id){
		logger.info("����ID��ѯ��������...");
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
