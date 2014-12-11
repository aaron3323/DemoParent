package com.demo.subsystem.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.pool.DruidDataSource;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: 数据源辅助类 </p> 
 * <p>Author:aaron</p>
 */
public class DataSourceHelper {
	private static final Log log = LogFactory.getLog(DataSourceHelper.class);
	
	private final static ThreadLocal<Connection> readonly_connection = new ThreadLocal<Connection>();
	
	public final static Connection getReadOnlyConnection(DruidDataSource dataSource) {
		Connection conn = readonly_connection.get();
		
		try {
			if (conn == null) {
				conn = dataSource.getConnection();
				conn.setReadOnly(true);

				readonly_connection.set(conn);
			}
		} catch (Exception e) {
			log.error("Unabled to get connection!!! ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return conn;
	}

	/**
	 * 
	 * <br/>Description:关闭连接
	 * <p>Author:aaron</p>
	 */
	public final static void closeConnection() {
		Connection readonly_conn = readonly_connection.get();
		
		try {
			if (readonly_conn != null) {
				readonly_conn.close();
			}
		} catch (SQLException e) {
			log.error("Unabled to close connection!!! ", e);
			throw new RuntimeException(e.getMessage(), e);
		}

		readonly_connection.remove();
	}

}
