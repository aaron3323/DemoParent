package com.demo.subsystem.authentication.service.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 重写无权限处理，支持json </p> 
 * <p>Author:aaron</p>
 */
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	
	private String accessDeniedUrl;
	
	public AuthenticationAccessDeniedHandler(){}
	
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public AuthenticationAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl=accessDeniedUrl;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));  
        
		//如果是ajax请求  
		if (isAjax) {         
			String jsonObject = "{\"message\":\"You are not privileged to request this resource.\",\"access-denied\":true,\"cause\":\"AUTHORIZATION_FAILURE\"}";  
			String contentType = "application/json";  
			response.setContentType(contentType);  
		    PrintWriter out = response.getWriter();  
		    out.print(jsonObject);  
		    out.flush();  
		    out.close();  
		    return;  
		} else {  
			RequestDispatcher dispatcher = request.getRequestDispatcher(accessDeniedUrl);
            dispatcher.forward(request, response);
		}
	}
}
