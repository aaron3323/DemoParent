package com.demo.subsystem.demo.service;

import com.demo.subsystem.common.BaseService;
import com.demo.subsystem.demo.mapper.Dept;

public interface IDeptService extends BaseService {
	Dept findOneToMany();
}
