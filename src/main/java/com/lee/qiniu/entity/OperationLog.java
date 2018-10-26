package com.lee.qiniu.entity;

import java.util.Date;

public class OperationLog {
	private Integer id;
	private Date logDateTime;
	private String moduleName;
	private String actionName;
	private String methodName;
	private String params;
	private Integer operUserId;
	private String operUrl;
	private String operIp;
	private String operLocation;
	private Boolean sussess;
	private String errorMsg;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLogDateTime() {
		return logDateTime;
	}
	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Integer getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}
	public String getOperUrl() {
		return operUrl;
	}
	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}
	public String getOperIp() {
		return operIp;
	}
	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}
	public String getOperLocation() {
		return operLocation;
	}
	public void setOperLocation(String operLocation) {
		this.operLocation = operLocation;
	}
	public Boolean getSussess() {
		return sussess;
	}
	public void setSussess(Boolean sussess) {
		this.sussess = sussess;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
