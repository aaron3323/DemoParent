package com.demo;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorTest {
    public static void main(String[] args) throws Exception {  
        // 监控目录  
        String rootDir = "d:\\Temp";  
        // 轮询间隔 5 秒  
        long interval = TimeUnit.SECONDS.toMillis(5);  
        //   
        FileAlterationObserver observer = new FileAlterationObserver(  
                                              rootDir,   
                                              FileFilterUtils.and(  
                                               FileFilterUtils.fileFileFilter(),  
                                               FileFilterUtils.suffixFileFilter(".java")),   
                                              null);  
        observer.addListener(new MyFileListener());  
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);  
        // 开始监控  
        monitor.start();  
    }  
}
