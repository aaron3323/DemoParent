package com.demo.subsystem.demo2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.common.DbUtilsTemplate;
import com.demo.subsystem.demo.mapper.User;
import com.demo.subsystem.demo2.service.IDemo2Service;

@Service("Demo2ServiceImpl")
public class Demo2ServiceImpl extends BaseServiceImpl implements IDemo2Service {

	@Override
	public void updateUser1() throws Exception {
		String sql = "update User set deptId = 'd1' where id= 'admin'";
		dbUtilsTemplate.update(sql);
	}

	@Override
	public void updateUser2() throws Exception {
		String sql = "update User set deptId = 'd2' where id= '46c53ec21934415d914cc4e55f83d109'";
		dbUtilsTemplate.update(sql);
//		if(true) {
//			throw new Exception("测试事务");
//		}
	}

	@Override
	public void updateUser0() throws Exception {
		this.updateUser1();
		this.updateUser2();
		String sql = "select * from User";
		List<User> userList = dbUtilsTemplate.find(User.class, sql);
		System.out.println(userList.size());
	}


}
