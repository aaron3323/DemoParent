package com.demo.subsystem.authentication.service.impl;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 访问决策管理器 </p> 
 * <p>Author:aaron</p>
 */
public class AuthorizationAccessDecisionManager implements AccessDecisionManager {
	private final Log log = LogFactory.getLog(AuthorizationAccessDecisionManager.class);
	
	/**
	 * 没配置权限的资源，默认是否可以访问
	 */
	@Inject
	private Boolean defaultAuthorization;
	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		//没有设置访问权限，并且defaultAuthorization=true，允许访问
		if(CollectionUtils.isEmpty(configAttributes) && defaultAuthorization) {
			return;
		}
		
		for (ConfigAttribute attribute : configAttributes) {
			String attr = attribute.getAttribute();
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				String auth = authority.getAuthority();
				if (attr.equals(auth)) {
					return;
				}
			}
		}
		
		//没有访问权限
		throw new AccessDeniedException("No Access Dendied");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
