package com.demo.subsystem.common;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

public class FileMonitorListener extends FileAlterationListenerAdaptor {

	@Override
	public void onFileCreate(File file) {
		System.out.println("onFileCreate");
	}

	@Override
	public void onFileChange(File file) {
		System.out.println("onFileChange");
	}

}
