package com.demo;

import java.io.File;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

import com.demo.subsystem.common.ConfigurationProperties;

public class MonitorJunit {

	@Test
	public void testMonitor () {
		URL url = MonitorJunit.class.getResource("/log4j.properties");
		try {
			ConfigurationFileChangedReloadingStrategy fileChangedReloadingStrategy = new ConfigurationFileChangedReloadingStrategy();
			fileChangedReloadingStrategy.setRefreshDelay(3000);
			ConfigurationProperties.load("aa", url.getPath(), true, fileChangedReloadingStrategy);
			
			
			
			
			System.out.println("end");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMonitor2() {
        try {
			URL url = MonitorJunit.class.getResource("/log4j.properties");
			File file = new File(url.getPath());
			FileAlterationObserver fileObserver = new FileAlterationObserver(file);
			fileObserver.addListener(new CacheFileAlternationListener());
			FileAlterationMonitor fm = new FileAlterationMonitor(3 * 1000, fileObserver);  
			fm.start();

			System.out.println("end");
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
