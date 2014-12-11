package com.demo.subsystem.demo.entity;

import com.demo.subsystem.common.BaseQueryParam;
import com.demo.subsystem.demo.mapper.User;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo查询参数 </p> 
 * <p>Author:aaron</p>
 */
public class DemoQueryParam extends BaseQueryParam {
	private static final long serialVersionUID = -8904603062420663213L;
	
	public User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
