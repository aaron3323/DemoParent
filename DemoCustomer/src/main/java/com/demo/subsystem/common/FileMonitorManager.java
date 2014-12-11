package com.demo.subsystem.common;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorManager {

	public static void addListener(String file) {
		try {
			long interval = TimeUnit.SECONDS.toMillis(5);
			FileAlterationObserver observer = new FileAlterationObserver(file);
			
			observer.addListener(new FileMonitorListener());
			FileAlterationMonitor monitor = new FileAlterationMonitor(5,observer);  
	        // 开始监控
			monitor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
