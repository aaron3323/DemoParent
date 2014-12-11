package com.demo.subsystem.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 控制器基类 </p> 
 * <p>Author:aaron</p>
 */
public abstract class BaseController {
	
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, HttpSession session) { 
		this.request = request; 
		this.response = response;
		this.session = session;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, false));  
    }
	
}
