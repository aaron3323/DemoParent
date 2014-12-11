package com.demo.subsystem.authentication.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.authentication.service.IAuthenticationService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 登陆验证 </p> 
 * <p>Author:aaron</p>
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Log log = LogFactory.getLog(LoginAuthenticationFilter.class);
	
	@Autowired
	@Qualifier("AuthenticationServiceImpl")
	private IAuthenticationService authenticationService;
	
	/**
	 * <p>Description:登陆验证</p>
	 * <p>Author:aaron</p>
	 * @Title: attemptAuthentication
	 * @param request
	 * @param response
	 * @return
	 * @throws AuthenticationException
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
		
//		Enumeration<String> enu=request.getParameterNames();
//		while(enu.hasMoreElements()){  
//			String paraName=enu.nextElement();  
//			System.out.println(paraName+": "+request.getParameter(paraName));  
//		}
		//throw new AuthenticationServiceException("");
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		Users users = null;
		try {
			users = authenticationService.findUserByUsername(username);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AuthenticationServiceException("Authentication failed: " + e.getMessage());
		}
		
		if(null == users) {
			throw new AuthenticationServiceException("Account does not exist");
		}
		
		if(!password.equals(users.getPassword())) {
			throw new AuthenticationServiceException("Password mistake");
		}
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		setDetails(request, authRequest);
		
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
