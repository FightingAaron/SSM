package com.quartz.bean;

import java.util.Date;


//定时任务任务类
public class TaskBean {

	private String id;
	private String name;
	private String type;
	private String status;
	private String actualStatus;//定时任务实际状态 数据库不存在该字段
	private String ifAutoBoot;
	private String executionPolicy;
	private String taskClass;
	private String description;
	private Date createDate;
	private Date updateDate;
	private String operator;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getActualStatus() {
		return actualStatus;
	}
	public void setActualStatus(String actualStatus) {
		this.actualStatus = actualStatus;
	}
	public String getIfAutoBoot() {
		return ifAutoBoot;
	}
	public void setIfAutoBoot(String ifAutoBoot) {
		this.ifAutoBoot = ifAutoBoot;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getExecutionPolicy() {
		return executionPolicy;
	}
	public void setExecutionPolicy(String executionPolicy) {
		this.executionPolicy = executionPolicy;
	}
	@Override
	public String toString() {
		return "TaskBean [id=" + id + ", name=" + name + ", type=" + type
				+ ", status=" + status + ", ifAutoboot=" + ifAutoBoot
				+ ", executionpolicy=" + executionPolicy + ", taskClass="
				+ taskClass + ", description=" + description + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", operator="
				+ operator + "]";
	}
	
		
}
