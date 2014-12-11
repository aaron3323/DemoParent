package com.demo.subsystem.common;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: service异常类 </p> 
 * <p>Author:aaron</p>
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1620954094087544139L;

	public ServiceException(){}
	
    public ServiceException(String message) {
        super(message);
    }
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
