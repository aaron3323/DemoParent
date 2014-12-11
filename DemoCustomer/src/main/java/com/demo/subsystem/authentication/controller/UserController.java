package com.demo.subsystem.authentication.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.subsystem.common.BaseController;

@Scope("prototype")
@Controller  
@RequestMapping(value="/user")
public class UserController extends BaseController {
//	@RequestMapping(params = "myjsp")  
//    public String home() {  
//        Subject currentUser = SecurityUtils.getSubject();  
//        if(currentUser.isPermitted("user.do?myjsp")){  
//            return "/my";  
//        }else{  
//            return "error/noperms";  
//        }  
//    }
}
