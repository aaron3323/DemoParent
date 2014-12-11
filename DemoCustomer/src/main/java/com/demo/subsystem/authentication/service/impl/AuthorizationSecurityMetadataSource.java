package com.demo.subsystem.authentication.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.demo.subsystem.authentication.service.IAuthorizationService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 提供某个资源对应的权限定义 </p> 
 * <p>Author:aaron</p>
 */
public class AuthorizationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private final Log log = LogFactory.getLog(AuthorizationSecurityMetadataSource.class);
	
	/**
	 * 安全框架启用标识
	 */
	@Inject
	private Boolean enabledSecurity;
	
	/**
	 * 没配置权限的资源，默认是否可以访问
	 */
	@Inject
	private Boolean defaultAuthorization;
	
	@Autowired
	@Qualifier("AuthorizationServiceImpl")
	private IAuthorizationService authorizationService;
	
	/**
	 * <p>Description:获取url资源的访问权限</p>
	 * <p>Author:aaron</p>
	 * @Title: getAttributes
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (this.enabledSecurity) {
			FilterInvocation invocation = (FilterInvocation) object;
			String url = invocation.getRequestUrl();
			HttpServletRequest request = invocation.getHttpRequest();
			Map<String, Collection<ConfigAttribute>> resourceMap = this.getAllResource();
			for (Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
				String key = entry.getKey();
				RequestMatcher matcher = new AntPathRequestMatcher(key);
				if (matcher.matches(request)) {
					Collection<ConfigAttribute> value = entry.getValue();
					return value;
				}
			}
			
			if(!defaultAuthorization) {
				String msg = url+" Without permission, not allowed to access";
				log.error(msg);
				throw new AccessDeniedException(msg);
			}
		}
		
		//返回null，直接访问资源
		return null;
	}

	/**
	 * <p>Description:取到所有资源及其对应角色的定义</p>
	 * <p>Author:aaron</p>
	 * @Title: getAllConfigAttributes
	 * @return
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> configAttribute = new HashSet<ConfigAttribute>();
		if (this.enabledSecurity) {
			Map<String, Collection<ConfigAttribute>> resourceMap = this.getAllResource();
			for(Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
				configAttribute.addAll(entry.getValue());
			}
		}
		
		return configAttribute;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Map<String, Collection<ConfigAttribute>> getAllResource() {
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		try {
			authorizationService.reloadAllResource();
			resourceMap = authorizationService.getAllResource();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return resourceMap;
	}

}
