package com.demo.subsystem.authentication.entity;

import java.io.Serializable;

public class Users implements Serializable {
	private static final long serialVersionUID = 3727399502265593032L;
	
	private String id;
	private boolean enabled;
    private String password;
    private String username;
	    
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
