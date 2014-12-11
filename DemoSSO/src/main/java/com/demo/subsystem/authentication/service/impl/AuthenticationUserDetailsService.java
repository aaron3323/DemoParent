package com.demo.subsystem.authentication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.subsystem.authentication.entity.Resource;
import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.authentication.service.IAuthenticationService;
import com.demo.subsystem.authentication.service.IAuthorizationService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 实现UserDetailsService接口 </p> 
 * <p>Author:aaron</p>
 */
@Service("authenticationUserDetailsService")
public class AuthenticationUserDetailsService implements UserDetailsService {
	private final Log log = LogFactory.getLog(AuthenticationUserDetailsService.class);
	
	@Autowired
	@Qualifier("AuthenticationServiceImpl")
	private IAuthenticationService authenticationService;
	
	@Autowired
	@Qualifier("AuthorizationServiceImpl")
	private IAuthorizationService authorizationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userdetail = null;
		
		try {
			Users users = authenticationService.findUserByUsername(username);
			if(null == users) {
				throw new UsernameNotFoundException("Account does not exist");
			}
			
			List<Resource> resourceList = authorizationService.findResource(users.getId());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(Resource resource : resourceList) {
				authorities.add(new SimpleGrantedAuthority(resource.getAccess()));
			}
			userdetail = new User(username, users.getPassword(), authorities);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage(), e);
		}
		
		return userdetail;
	}

}
