package com.demo.subsystem.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: service方法通知 </p> 
 * <p>Author:aaron</p>
 */
//@Aspect
//TODO service通知，暂时禁用
public class ServiceAdvice {
	
	/**
	 * <br/>Description:切入点
	 * <p>Author:aaron</p>
	 */
	@Pointcut(value="execution(* com.demo.subsystem.*.service.*.*(..))")
	private void anyMethod(){}

	/**
	 * <br/>Description:最终通知
	 * <p>Author:aaron</p>
	 */
	@After(value="anyMethod()")
	public void afterReturning() {
		System.out.println("最终通知");
		DataSourceHelper.closeConnection();
	}

}
