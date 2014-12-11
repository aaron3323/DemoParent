package com.demo.subsystem.demo.service.impl;

import org.springframework.stereotype.Service;

import com.demo.subsystem.common.BaseServiceImpl;
import com.demo.subsystem.demo.mapper.Dept;
import com.demo.subsystem.demo.mapper.DeptMapper;
import com.demo.subsystem.demo.service.IDeptService;

@Service("DeptServiceImpl")
public class DeptServiceImpl extends BaseServiceImpl implements IDeptService {

	@Override
	public Dept findOneToMany() {
		DeptMapper deptrMapper = sqlSession.getMapper(DeptMapper.class);
		Dept dept = deptrMapper.findOneToMany("d1");
//		System.out.println(user.getDept().getDeptname());
		return dept;
	}

}
