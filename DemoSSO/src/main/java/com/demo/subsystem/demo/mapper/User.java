package com.demo.subsystem.demo.mapper;

import java.io.Serializable;
import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 实体类--表 </p> 
 * <p>Author:aaron</p>
 */
public class User implements Serializable {
	private static final long serialVersionUID = 5414853814315511374L;

	private String id;

    private String username;

    private String password;

    private String mail;

    private String deptId;
    
    private Dept dept;
    
    private List<Orders> ordersList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public List<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

}