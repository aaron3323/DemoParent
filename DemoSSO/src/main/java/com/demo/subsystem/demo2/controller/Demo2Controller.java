package com.demo.subsystem.demo2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.subsystem.common.BaseController;
import com.demo.subsystem.demo.mapper.User;
import com.demo.subsystem.demo2.service.IDemo2Service;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo2控制器 </p> 
 * <p>Author:aaron</p>
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "/demo2")
public class Demo2Controller extends BaseController {
	private static final Log log = LogFactory.getLog(Demo2Controller.class);
	
	@Autowired
	@Qualifier("Demo2ServiceImpl")
	private IDemo2Service demo2Service;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> demo(HttpSession session, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			demo2Service.updateUser0();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return map;
	}

}
