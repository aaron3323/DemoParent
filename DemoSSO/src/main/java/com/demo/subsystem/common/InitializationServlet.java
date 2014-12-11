package com.demo.subsystem.common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet(name="initialization",urlPatterns="/initialization", loadOnStartup=1)
public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1716448391540046198L;

	@Override
	public void init() throws ServletException {
		super.init();
		
		String ctx=this.getServletConfig().getServletContext().getContextPath();
		Utils.setCtx(ctx);
	}


}
