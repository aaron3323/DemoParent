package com.demo.subsystem.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.subsystem.common.BaseController;
import com.demo.subsystem.demo.service.IDemoService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo控制器 </p> 
 * <p>Author:aaron</p>
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController {
	private static final Log log = LogFactory.getLog(DemoController.class);
	
	@Autowired
	@Qualifier("DemoServiceImpl")
	private IDemoService demoService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> demo(HttpSession session, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			demoService.update();
			int i = demoService.count();
			log.info("i="+i);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return map;
	}
	
	@RequestMapping(value="/jwplayer", method=RequestMethod.GET)
	public String dempJWPlayer() {
		//return "redirect:/demo/jwplayer2";
		return "jwplayer";
	}
	
	@RequestMapping(value="/jwplayer2", method=RequestMethod.GET)
	public String dempJWPlayer2() {
		return "jwplayer2";
	}
}
