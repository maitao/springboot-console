package com.mt.console.web.po;

import java.sql.Timestamp;

public class Permission {

	private String id;
	private String number;
	private String methodNum;
	private String name;
	private String classPath;
	private String methodName;
	private int isAjax;
	private int isMenu;
	private int status;
	private String methodRemark;
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMethodNum() {
		return methodNum;
	}

	public void setMethodNum(String methodNum) {
		this.methodNum = methodNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(int isAjax) {
		this.isAjax = isAjax;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

}
