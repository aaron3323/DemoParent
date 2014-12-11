package com.demo;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

public class CacheFileAlternationListener extends FileAlterationListenerAdaptor {

	@Override
	public void onFileChange(File file) {
		System.out.println("onFileChange");
	}

}
