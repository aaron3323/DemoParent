package com.demo.subsystem.demo.mapper;

import java.io.Serializable;

public class Orders implements Serializable {
	private static final long serialVersionUID = 8586054465638301060L;

	private String id;
	private String name;
	private String userId;
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
