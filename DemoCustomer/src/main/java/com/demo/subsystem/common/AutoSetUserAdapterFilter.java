package com.demo.subsystem.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

public class AutoSetUserAdapterFilter implements Filter {
	private final Log log = LogFactory.getLog(AutoSetUserAdapterFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Object object = httpRequest.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		
		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();
			log.info("loginName: "+loginName);
//			User user = UserUtil.getCurrentUser(httpRequest.getSession());
//
//			// 第一次登录系统
//			if (user == null) {
//				WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getSession().getServletContext());
//				UserManager userManager = (UserManager) wct.getBean("userManager");
//				user = userManager.findUserByLoginName(loginName);
//				// 保存用户信息到Session
//				UserUtil.saveUserToSession(httpRequest.getSession(), user);
//			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
