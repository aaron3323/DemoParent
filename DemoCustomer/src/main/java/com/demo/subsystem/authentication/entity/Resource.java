package com.demo.subsystem.authentication.entity;

import java.io.Serializable;

public class Resource implements Serializable {
	private static final long serialVersionUID = -7973540721572289909L;

	private String id;
	private String pid;
	private boolean enabled;
	private String resourcename;
	private String resourcetype;
	private String resourceurl;
	private String access;
	private String target;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}
	public String getResourceurl() {
		return resourceurl;
	}
	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
