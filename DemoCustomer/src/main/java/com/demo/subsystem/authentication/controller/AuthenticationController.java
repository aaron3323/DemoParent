package com.demo.subsystem.authentication.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.subsystem.common.BaseController;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 认证 </p> 
 * <p>Author:aaron</p>
 */
@Scope("prototype")
@Controller
public class AuthenticationController extends BaseController {
	private static final Log log = LogFactory.getLog(AuthenticationController.class);
	//TODO
	//ajax
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String gotoLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if (error == true) {
            model.put("error", "You have entered an invalid username or password!");
        } else {
            model.put("error", "");
        }
		return "login";
	}
	
	@RequestMapping(value="/expired", method=RequestMethod.GET)
	public String expired() {
		return "expired";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)  
    public String getDeniedPage() {  
        return "deniedpage";  
    }
	
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)  
	public String timeout() {
		return "timeout";
	}
	
	@RequestMapping(value = "/castimeout", method = RequestMethod.GET)  
	public String castimeout() {
		return "castimeout";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String gotoMainPage() {
		return "test";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "logout";
	}

//	@ResponseBody
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public Map<String, Object> login(User user, @RequestParam(value = "error", required = false) boolean error) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		Subject currentUser = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsercode(), DigestUtils.md5Hex(user.getPassword()));
//		token.setRememberMe(true);
//		try {
//			currentUser.login(token);
//		} catch (AuthenticationException e) {
//			e.printStackTrace();
//
//		}
//		if (currentUser.isAuthenticated()) {
//			map.put("isOk", true);
//		} else {
//			map.put("isOk", false);
//		}
//		return map;
//	}
}
