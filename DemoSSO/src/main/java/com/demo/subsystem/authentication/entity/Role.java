package com.demo.subsystem.authentication.entity;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 6957460156514788372L;
	
	private String id;
	private boolean enabled;
	private String rolename;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
