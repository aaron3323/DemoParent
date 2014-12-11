package com.demo.subsystem.authentication.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.demo.subsystem.authentication.entity.Resource;
import com.demo.subsystem.authentication.repository.IAuthenticationRepository;
import com.demo.subsystem.authentication.service.IAuthorizationService;
import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.common.ServiceException;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 授权service </p> 
 * <p>Author:aaron</p>
 */
@Service("AuthorizationServiceImpl")
public class AuthorizationServiceImpl extends BaseServiceImpl implements IAuthorizationService {
	private final Log log = LogFactory.getLog(AuthorizationServiceImpl.class);
	
	@Autowired
	@Qualifier("AuthenticationRepositoryImpl")
	private IAuthenticationRepository authenticationRepository;

	@Override
	public List<Resource> findResource(String userid) throws ServiceException {
		List<Resource> list = null;
		
		try {
			list = authenticationRepository.findResourceByUserId(userid);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return list;
	}

	@Override
	public void reloadAllResource() throws ServiceException {
		try {
			authenticationRepository.reloadAllResource();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Map<String, Collection<ConfigAttribute>> getAllResource() throws ServiceException {
		Map<String, Collection<ConfigAttribute>> map = null;
		
		try {
			map = authenticationRepository.getAllResource();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
		
		return map;
	}

}
