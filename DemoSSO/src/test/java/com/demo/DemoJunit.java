package com.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.subsystem.common.Pagination;
import com.demo.subsystem.demo.mapper.Dept;
import com.demo.subsystem.demo.mapper.User;
import com.demo.subsystem.demo.service.IDemoService;
import com.demo.subsystem.demo.service.IDeptService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mybatis.xml"})
public class DemoJunit {
	
	@Autowired
	@Qualifier("DemoServiceImpl")
	private IDemoService demoService;
	
	@Autowired
	@Qualifier("DeptServiceImpl")
	private IDeptService deptService;

	@Test
	public void testInsert() {
		try {
			int i = demoService.insert();
			demoService.update();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertBatch() {
		try {
			int i = demoService.insertBatch();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		try {
			int i = demoService.delete();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			int i = demoService.update();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCount() {
		try {
			int i = demoService.count();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindById() {
		try {
			User user = demoService.findById();
			if(null == user) {
				System.out.println("null");
			}else {
				System.out.println(user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFind() {
		try {
			User user = new User();
			user.setUsername("admin");
			
			List<User> userList = demoService.find(user);
			System.out.println(userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAll() {
		try {
			List<User> userList = demoService.findAll();
			System.out.println(userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindPage() {
		try {
			Pagination<User> page = demoService.findPage(2,3);
			List<User> list = page.getContent();
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindLeftJoin() {
		try {
			List<User> list = demoService.findAssociation();
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOneToOne() {
		try {
			User user = demoService.findOneToOne();
			System.out.println(user.getDept().getDeptname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOneToMany() {
		try {
			Dept dept = deptService.findOneToMany();
			System.out.println(dept.getUserList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
