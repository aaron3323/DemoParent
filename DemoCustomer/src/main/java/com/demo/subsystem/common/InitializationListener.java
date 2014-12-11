package com.demo.subsystem.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 系统初始化 </p> 
 * <p>Author:aaron</p>
 */
public class InitializationListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(InitializationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("Initialization System...");
		
		//获取绝对路径
		String absolutePath = event.getServletContext().getRealPath("/");
		Utils.setAbsolutePath(absolutePath);
		
		String ctx = event.getServletContext().getServletContextName();
		Utils.setCtx(ctx);
		
		InputStream is = null;
		try {
			//读取系统配置信息
			is = this.getClass().getResourceAsStream("/system_config.properties");
			Properties systemProperties = new Properties();
			systemProperties.load(is);
			Utils.setSystemProperties(systemProperties);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.info("Destroyed System...");
	}

}
