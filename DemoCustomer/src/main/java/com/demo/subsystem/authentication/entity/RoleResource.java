package com.demo.subsystem.authentication.entity;

import java.io.Serializable;

public class RoleResource implements Serializable {
	private static final long serialVersionUID = -3469061350722558069L;

	private String id;
	private String roleid;
	private String resourceid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
}
