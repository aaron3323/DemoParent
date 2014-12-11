package com.demo.subsystem.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

public class TestFilter implements Filter {

	@Autowired(required=false)
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		String sessionId = req.getSession().getId();
		System.out.println("current seessionid: " + req.getSession().getId());
//		SecurityContextHolder.clearContext();
		if(req.getRequestURI().endsWith("j_spring_cas_security_check")) {
			
			System.out.println(req.getRequestURI() + " | " + req.getMethod());
			Enumeration<String> e = req.getParameterNames();
			while(e.hasMoreElements()){  
				String paraName=e.nextElement();  
				System.out.println(paraName+": "+request.getParameter(paraName));  
			}
		}
		
		if(null != sessionRegistry) {
			List<Object> list = sessionRegistry.getAllPrincipals();
			System.out.println("当前登陆用户数："+list.size());
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
