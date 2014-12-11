package com.demo.subsystem.common;

import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 工具类 </p> 
 * <p>Author:aaron</p>
 */
public class Utils {

	/**
	 * 绝对路径
	 */
	private static String absolutePath;
	
	private static String ctx;
	
	private static Properties systemProperties;
	
	static {
		//静态块
	}
	
	/**
	 * <br/>Description:生成uuid
	 * <p>Author:aaron</p>
	 * @return
	 */
	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		
		return uuid;
	}
	
	/** 
	 * <p>Copyright: All Rights Reserved</p>  
	 * <p>Description: 线程池 </p> 
	 * <p>Author:aaron</p>
	 */
	public static class ExecutorsService {
		private static ExecutorService es = null;
		
		static {
			es = Executors.newCachedThreadPool();
			//es = Executors.newFixedThreadPool(5);
		}
		
		static public void execute(Runnable command) {
			es.execute(command);
		}
		
		static public <T> Future<T> submit(Callable<T> task) {
			return es.submit(task);
		}
	}

	/**
	 * <br/>Description:判断操作系统
	 * <p>Author:aaron</p>
	 * @return
	 */
	public static boolean isWindowsOS(){
		boolean boo = false;
		try{
			Properties sp = System.getProperties();
			String osName = sp.getProperty("os.name");
			int osInt = osName.toLowerCase().indexOf("win");

			if(osInt != - 1){
				boo = true;
			}
		}catch(Exception e){
			System.out.println("获取操作系统名称异常。");
		}

		return boo;
	}

	/**
	 * <br/>Description:获取根路径
	 * <p>Author:aaron</p>
	 * @return
	 */
	static public String getRootPath() {
		String rootPath = null;
		if(Utils.isWindowsOS()) {
			rootPath = "c:/";
		}else {
			rootPath = "/etc/";
		}
		
		return rootPath;
	}

	/**
	 * 
	 * <br/>Description:获取ip
	 * <p>Author:aaron</p>
	 * @param request
	 * @return
	 */
	static public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)?"127.0.0.1":ip;
    }
	
	/**
	 * <br/>Description:读取配置文件
	 * <p>Author:aaron</p>
	 * @param fileName
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getProperties(String fileName, String key) throws Exception{
		java.util.Properties properties = new java.util.Properties();
		String rootPath = Utils.getRootPath();
		
		java.io.FileInputStream fileStream = new java.io.FileInputStream(rootPath+"icloudq/"+fileName);
		properties.load(fileStream);
		fileStream.close();
		String value = properties.getProperty(key);
		
		return value;
	}
	
	/**
	 * <br/>Description:正则验证
	 * <p>Author:aaron</p>
	 * @param expression
	 * @param input
	 * @return
	 */
	public static boolean regex(String expression,String input){
		Pattern pattern = Pattern.compile(expression);
		Matcher m =pattern.matcher(input);
		return m.matches();
	}

	/**
	 * <br/>Description:获取分页对象
	 * <p>Author:aaron</p>
	 * @param content
	 * @param pageNo
	 * @param pageSize
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> Pagination<T> getPage(List<T> content ,int pageNo,int pageSize,int count) throws Exception{
		Pagination<T> page = new Pagination<>();
		page.setContent(content);
		// 起始记录条数
		int elementsNo = (pageNo - 1) * pageSize + 1;
		page.setElementsNo(elementsNo);
		// 查询记录条数
		page.setLimit(pageSize);
		// 当前页码
		page.setNumber(pageNo);
		// 总记录数
		int totalElements = count;
		page.setTotalElements(totalElements);
		// 总页数
		int totalPages = 0;
		if(totalElements > 0){
			if(totalElements % pageSize == 0){
				totalPages = totalElements / pageSize;
			}else{
				totalPages = (totalElements / pageSize) + 1;
			}
		}
		page.setTotalPages(totalPages);
		return page;
	}
	
	/**
	 * <br/>Description:防sql注入，true有注入，false无注入
	 * <p>Author:aaron</p>
	 * @param str
	 * @return
	 */
	public static boolean sqlValidate(String str){
		String reg = "(?:\\//)|(?:\\/)|(?:\\#)|(?:\\%)|(?:\\*)|(?:,)|(?:;)|(?:')|(?:-)|(?:\\+)|(?:\\=)|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" +
				"(\\b(select|update|and|or|delete|insert|trancate|char|chr|into|substr|ascii|declare|exec|count|mid|master|into|create|drop|" +
				"execute|union|where|order|by|like|group)\\b)";		
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if(sqlPattern.matcher(str).find()){
			return true;
		}
		return false;
	}
	
	public static Properties getDruidProperties() {
		Properties properties = new Properties(); 
		
		for (Object key : systemProperties.keySet()) {
			String skey = (String) key;
			if (skey.startsWith("jdbc.")) {
				String name = skey.substring(5);
				properties.put(name, systemProperties.getProperty(skey));
			}
		}
		
		return properties;
	}

	public static String getCtx() {
		return ctx;
	}

	public static void setCtx(String ctx) {
		System.setProperty("ctx", ctx);
		Utils.ctx = ctx;
	}

	public static Properties getSystemProperties() {
		return systemProperties;
	}

	public static void setSystemProperties(Properties systemProperties) {
		Utils.systemProperties = systemProperties;
	}

	public static String getAbsolutePath() {
		return absolutePath;
	}

	public static void setAbsolutePath(String absolutePath) {
		Utils.absolutePath = absolutePath;
		System.setProperty("absolutePath", absolutePath);
	}

}
