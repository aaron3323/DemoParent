package com.demo.subsystem.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: service基类 </p> 
 * <p>Author:aaron</p>
 */
abstract public class BaseServiceImpl implements BaseService {
	@Autowired(required=false)
	@Qualifier("sqlSession")
	protected SqlSession sqlSession;

	@Autowired(required=false)
	@Qualifier("sqlSessionBatch")
	protected SqlSession sqlSessionBatch;
	
	@Autowired(required=false)
	@Qualifier("dbUtilsTemplate")
	protected DbUtilsTemplate dbUtilsTemplate;
	
	@Autowired(required=false)
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
}
