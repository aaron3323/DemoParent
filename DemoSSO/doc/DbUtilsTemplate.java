package com.demo.subsystem.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: DBUtils模板 </p> 
 * <p>Author:aaron</p>
 */
public class DbUtilsTemplate {
	private static final Log log = LogFactory.getLog(DbUtilsTemplate.class);
	
	private final static QueryRunner queryRunner = new QueryRunner();

	private static final Connection getConnection() {
		Connection connection = null;
		connection = DataSourceHelper.getConnection();
		
		return connection;
	}

	/** 
     * 执行sql语句 
     * @param sql sql语句 
     * @return 受影响的行数 
     */ 
    public static int update(String sql) { 
        return update(sql, null); 
    } 
      
    /** 
     * 执行sql语句 
     * <code> 
     * executeUpdate("update user set username = 'kitty' where username = ?", "hello kitty"); 
     * </code> 
     * @param sql sql语句 
     * @param param 参数 
     * @return 受影响的行数 
     */ 
    public static int update(String sql, Object param) { 
        return update(sql, new Object[] { param }); 
    } 
      
    /** 
     * 执行sql语句 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 受影响的行数 
     */ 
    public static int update(String sql, Object[] params) { 
        int affectedRows = 0; 
        try {
            if (params == null) { 
                affectedRows = queryRunner.update(getConnection(), sql); 
            } else { 
                affectedRows = queryRunner.update(getConnection(), sql, params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to update data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return affectedRows; 
    } 
      
    /** 
     * 执行批量sql语句 
     * @param sql sql语句 
     * @param params 二维参数数组 
     * @return 受影响的行数的数组 
     */ 
    public static int[] batchUpdate(String sql, Object[][] params) { 
        int[] affectedRows = new int[0]; 
        try { 
            affectedRows = queryRunner.batch(getConnection(), sql, params); 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to batch update data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return affectedRows; 
    }     

    /** 
     * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中 
     * @param sql sql语句 
     * @return 查询结果 
     */ 
    public static List<Map<String, Object>> find(String sql) { 
        return find(sql, null); 
    } 
      
    /** 
     * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中 
     * @param sql sql语句 
     * @param param 参数 
     * @return 查询结果 
     */ 
    public static List<Map<String, Object>> find(String sql, Object param) { 
        return find(sql, new Object[] {param}); 
    } 
      
    /** 
     * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 查询结果 
     */ 
    public static List<Map<String, Object>> find(String sql, Object[] params) { 
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); 
        try { 
            if (params == null) { 
                list = (List<Map<String, Object>>) queryRunner.query(getConnection(), sql, new MapListHandler()); 
            } else { 
                list = (List<Map<String, Object>>) queryRunner.query(getConnection(), sql, new MapListHandler(), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return list; 
    } 
      
    /** 
     * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @return 查询结果 
     */ 
    public static <T> List<T> find(Class<T> entityClass, String sql) { 
        return find(entityClass, sql, null); 
    } 
      
    /** 
     * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param param 参数 
     * @return 查询结果 
     */ 
    public static <T> List<T> find(Class<T> entityClass, String sql, Object param) { 
        return find(entityClass, sql, new Object[] { param }); 
    } 
      
    /** 
     * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 查询结果 
     */ 
    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    public static <T> List<T> find(Class<T> entityClass, String sql, Object[] params) { 
        List<T> list = new ArrayList<T>(); 
        try { 
            if (params == null) { 
                list = (List<T>) queryRunner.query(getConnection(), sql, new BeanListHandler(entityClass)); 
            } else { 
                list = (List<T>) queryRunner.query(getConnection(), sql, new BeanListHandler(entityClass), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return list; 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成对象 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @return 对象 
     */ 
    public static <T> T findFirst(Class<T> entityClass, String sql) { 
        return findFirst(entityClass, sql, null); 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成对象 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param param 参数 
     * @return 对象 
     */ 
    public static <T> T findFirst(Class<T> entityClass, String sql, Object param) { 
        return findFirst(entityClass, sql, new Object[] { param }); 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成对象 
     * @param entityClass 类名 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 对象 
     */ 
    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    public static <T> T findFirst(Class<T> entityClass, String sql, Object[] params) { 
        Object object = null; 
        try { 
            if (params == null) { 
                object = queryRunner.query(getConnection(), sql, new BeanHandler(entityClass)); 
            } else { 
                object = queryRunner.query(getConnection(), sql, new BeanHandler(entityClass), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return (T) object; 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成Map对象 
     * @param sql sql语句 
     * @return 封装为Map的对象 
     */ 
    public static Map<String, Object> findFirst(String sql) { 
        return findFirst(sql, null); 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成Map对象 
     * @param sql sql语句 
     * @param param 参数 
     * @return 封装为Map的对象 
     */ 
    public static Map<String, Object> findFirst(String sql, Object param) { 
        return findFirst(sql, new Object[] { param }); 
    } 
      
    /** 
     * 查询出结果集中的第一条记录，并封装成Map对象 
     * @param sql sql语句 
     * @param params 参数数组 
     * @return 封装为Map的对象 
     */ 
    public static Map<String, Object> findFirst(String sql, Object[] params) { 
        Map<String, Object> map = null; 
        try { 
            if (params == null) { 
                map = (Map<String, Object>) queryRunner.query(getConnection(), sql, new MapHandler()); 
            } else { 
                map = (Map<String, Object>) queryRunner.query(getConnection(), sql, new MapHandler(), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return map; 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnName 列名 
     * @return 结果对象 
     */ 
    public static Object findBy(String sql, String columnName) { 
        return findBy(sql, columnName, null); 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnName 列名 
     * @param param 参数 
     * @return 结果对象 
     */ 
    public static Object findBy(String sql, String columnName, Object param) { 
        return findBy(sql, columnName, new Object[] { param }); 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnName 列名 
     * @param params 参数数组 
     * @return 结果对象 
     */ 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object findBy(String sql, String columnName, Object[] params) { 
        Object object = null; 
        try { 
            if (params == null) { 
                object = queryRunner.query(getConnection(), sql, new ScalarHandler(columnName)); 
            } else { 
                object = queryRunner.query(getConnection(), sql, new ScalarHandler(columnName), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return object; 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnIndex 列索引 
     * @return 结果对象 
     */ 
    public static Object findBy(String sql, int columnIndex) { 
        return findBy(sql, columnIndex, null); 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnIndex 列索引 
     * @param param 参数 
     * @return 结果对象 
     */ 
    public static Object findBy(String sql, int columnIndex, Object param) { 
        return findBy(sql, columnIndex, new Object[] { param }); 
    } 
      
    /** 
     * 查询某一条记录，并将指定列的数据转换为Object 
     * @param sql sql语句 
     * @param columnIndex 列索引 
     * @param params 参数数组 
     * @return 结果对象 
     */ 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object findBy(String sql, int columnIndex, Object[] params) { 
        Object object = null; 
        try { 
            if (params == null) { 
                object = queryRunner.query(getConnection(), sql, new ScalarHandler(columnIndex)); 
            } else { 
                object = queryRunner.query(getConnection(), sql, new ScalarHandler(columnIndex), params); 
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return object; 
    } 
    
    /**
     * <br/>Description:查询某一列数据，返回集合
     * <p>Author:CherryChen/陈燕</p>
     * @param sql
     * @param columnName
     * @param param
     * @return
     */
    @SuppressWarnings("deprecation")
	public static List<Object> findListBy(String sql, Object param) { 
        List<Object> list = null; 
        try { 
            if (param == null) { 
                list = queryRunner.query(getConnection(), sql, new ColumnListHandler<>()); 
            } else { 
                list = queryRunner.query(getConnection(),sql,param,new ColumnListHandler<>());
            } 
        } catch (SQLException e) { 
            log.error("Error occured while attempting to query data", e); 
            throw new RuntimeException(e.getMessage(), e);
        } 
        return list;
    }
    
    /**
     * <br/>Description:查询某一列数据，返回集合
     * <p>Author:CherryChen/陈燕</p>
     * @param sql
     * @param columnName
     * @param param
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Object> findListBy(String sql, Object[] params) { 
    	List<Object> list = null; 
    	try { 
    		if (params == null || params.length == 0) { 
    			list = queryRunner.query(getConnection(), sql, new ColumnListHandler<>()); 
    		} else { 
    			list = queryRunner.query(getConnection(),sql,params,new ColumnListHandler<>());
    		} 
    	} catch (SQLException e) { 
    		log.error("Error occured while attempting to query data", e); 
			throw new RuntimeException(e.getMessage(), e);
    	} 
    	return list;
    }
    
    
}
