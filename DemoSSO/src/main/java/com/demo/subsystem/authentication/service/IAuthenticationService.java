package com.demo.subsystem.authentication.service;

import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.common.BaseService;
import com.demo.subsystem.common.ServiceException;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 身份验证接口 </p> 
 * <p>Author:aaron</p>
 */
public interface IAuthenticationService extends BaseService {

	/**
	 * <br/>Description:通过username查询用户
	 * <p>Author:aaron</p>
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	public Users findUserByUsername(String username) throws ServiceException;
}
