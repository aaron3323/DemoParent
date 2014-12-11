package com.demo;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

public class MyFileListener extends FileAlterationListenerAdaptor {
	@Override  
    public void onFileChange(File file) {  
        System.out.println("[修改]:" + file.getAbsolutePath());  
    }  
}
