package com.demo.subsystem.authentication.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.authentication.repository.IAuthenticationRepository;
import com.demo.subsystem.authentication.service.IAuthenticationService;
import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.common.RepositoryException;
import com.demo.subsystem.common.ServiceException;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 身份验证 </p> 
 * <p>Author:aaron</p>
 */
@Service("AuthenticationServiceImpl")
public class AuthenticationServiceImpl extends BaseServiceImpl implements IAuthenticationService {
	private final Log log = LogFactory.getLog(AuthenticationServiceImpl.class);
	
	@Autowired
	@Qualifier("AuthenticationRepositoryImpl")
	private IAuthenticationRepository authenticationRepository;
	
	/**
	 * <p>Description:通过username查询用户</p>
	 * <p>Author:aaron</p>
	 * @Title: findUserByUsername
	 * @param username
	 * @return
	 * @throws ServiceException
	 * @see com.demo.subsystem.authentication.service.IAuthenticationService#findUserByUsernameAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Users findUserByUsername(String username) throws ServiceException {
		Users users = null;
		try {
			users = authenticationRepository.findUserByUserName(username);
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return users;
	}

}
