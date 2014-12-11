package com.demo.subsystem.demo.mapper;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {
	private static final long serialVersionUID = -3718371316465016311L;

	private String id;
	private String deptname;
	private List<User> userList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
