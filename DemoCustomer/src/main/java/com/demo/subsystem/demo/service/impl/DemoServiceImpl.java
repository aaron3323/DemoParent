package com.demo.subsystem.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.common.Pagination;
import com.demo.subsystem.common.ServiceException;
import com.demo.subsystem.common.Utils;
import com.demo.subsystem.demo.controller.DemoController;
import com.demo.subsystem.demo.entity.DemoQueryParam;
import com.demo.subsystem.demo.mapper.User;
import com.demo.subsystem.demo.mapper.UserMapper;
import com.demo.subsystem.demo.service.IDemoService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: demo service实现类 </p> 
 * <p>Author:aaron</p>
 */
@Service("DemoServiceImpl")
public class DemoServiceImpl extends BaseServiceImpl implements IDemoService {
	private static final Log log = LogFactory.getLog(DemoServiceImpl.class);

	@Override
	public int insert() throws Exception {
		User user = new User();
		user.setId(Utils.generateUUID());
		user.setUsername("aaron");
		user.setPassword("aaron");
		user.setMail("a@163.com");
		user.setDeptId(null);
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		int i = userMapper.insert(user);
		
		return i;
	}

	@Override
	public int insertBatch() throws Exception {
		List<User> list = new ArrayList<User>();
		for(int i=0; i<10; i++) {
			User user = new User();
			user.setId(Utils.generateUUID());
			user.setUsername(String.valueOf(i));
			user.setPassword(String.valueOf(i));
			user.setMail(String.valueOf(i));
			
			list.add(user);
		}
		
		UserMapper userMapper = sqlSessionBatch.getMapper(UserMapper.class);
		long start = System.currentTimeMillis();
		int i = userMapper.insertBatch(list);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		return i;
	}

	@Override
	public int delete() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		int i = userMapper.delete("9aa9009298b4454f916fd61cb98667d3");
		
		return i;
	}

	@Override
	public int update() throws ServiceException {
		int i = 0;
		try {
			User user = new User();
			user.setId("admin");
			//user.setUsername("aaron");
			//user.setPassword("aaron");
			//user.setMail("a@163.com");
			user.setDeptId("d1");
			
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			i = userMapper.update(user);
			if(true) {
				throw new RuntimeException("测试");
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return i;
	}

	@Override
	public int count() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		return userMapper.count();
	}

	@Override
	public User findById() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findById("admin");
		return user;
	}

	@Override
	public List<User> find(User user) throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapper.find(user);
		return userList;
	}

	@Override
	public List<User> findAll() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapper.findAll();
		return userList;
	}

	@Override
	public Pagination<User> findPage(int pageNo,int pageSize) throws Exception {
		int count = this.count();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User conditionsParam = new User();
		conditionsParam.setUsername("admin");
		
		DemoQueryParam param = new DemoQueryParam();
		param.setStart((pageNo-1)*pageSize);
		param.setSize(pageSize);
		//param.setUser(conditionsParam);
		
		List<User> userList = userMapper.findPage(param);
		
		Pagination<User> paging = new Pagination<User>(userList, pageNo, pageSize, count);
		
		return paging;
	}

	@Override
	public List<User> findAssociation() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findLeftJoin();
		
		return list;
	}

	@Override
	public User findOneToOne() throws Exception {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findOneToOne("admin");
//		System.out.println(user.getDept().getDeptname());
		return user;
	}


}
