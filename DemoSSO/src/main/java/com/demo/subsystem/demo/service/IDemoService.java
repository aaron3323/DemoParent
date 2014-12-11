package com.demo.subsystem.demo.service;

import java.util.List;

import com.demo.subsystem.common.BaseService;
import com.demo.subsystem.common.Pagination;
import com.demo.subsystem.common.ServiceException;
import com.demo.subsystem.demo.mapper.User;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo service接口 </p> 
 * <p>Author:aaron</p>
 */
public interface IDemoService extends BaseService {
	
	int insert() throws Exception;
	
	int insertBatch() throws Exception;

	int delete() throws Exception;
	
	int update() throws ServiceException;
	
	int count() throws Exception;
	
	User findById() throws Exception;
	
	List<User> find(User user) throws Exception;
	
	List<User> findAll() throws Exception;
	
	Pagination<User> findPage(int pageNo,int pageSize) throws Exception;
	
	List<User> findAssociation() throws Exception;
	
	User findOneToOne() throws Exception;

}
