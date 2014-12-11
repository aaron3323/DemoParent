package com.demo.subsystem.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 关闭connection连接 </p> 
 * <p>Author:aaron</p>
 */
public class CloseConnectionMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object rval = invocation.proceed();
		DataSourceHelper.closeConnection();
		return rval;
	}

}
