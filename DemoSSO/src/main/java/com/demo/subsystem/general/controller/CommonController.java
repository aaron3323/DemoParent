package com.demo.subsystem.general.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.subsystem.common.BaseController;

@Scope("prototype")
@Controller
@RequestMapping(value="/common")
public class CommonController extends BaseController {
	
	@RequestMapping(value="/error403", method=RequestMethod.GET)
	public String error403(ModelMap model) {
		return "error403";
	}
	
	@RequestMapping(value="/timeout", method=RequestMethod.GET)
	public String timeout(ModelMap model) {
		return "timeout";
	}

}
