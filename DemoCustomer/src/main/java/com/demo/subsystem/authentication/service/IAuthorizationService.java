package com.demo.subsystem.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.demo.subsystem.authentication.entity.Resource;
import com.demo.subsystem.common.BaseService;
import com.demo.subsystem.common.ServiceException;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 授权service </p> 
 * <p>Author:aaron</p>
 */
public interface IAuthorizationService extends BaseService {

	/**
	 * <br/>Description:查询用户资源
	 * <p>Author:aaron</p>
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public List<Resource> findResource(String userid) throws ServiceException;

	public void reloadAllResource() throws ServiceException;

	public Map<String, Collection<ConfigAttribute>> getAllResource() throws ServiceException;
}
