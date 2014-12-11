package com.demo.subsystem.authentication.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Repository;

import com.demo.subsystem.authentication.entity.Resource;
import com.demo.subsystem.authentication.entity.Role;
import com.demo.subsystem.authentication.entity.RoleResource;
import com.demo.subsystem.authentication.entity.UserRole;
import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.authentication.repository.IAuthenticationRepository;
import com.demo.subsystem.common.BaseRepositoryImpl;
import com.demo.subsystem.common.RepositoryException;

@Repository("AuthenticationRepositoryImpl")
public class AuthenticationRepositoryImpl extends BaseRepositoryImpl implements IAuthenticationRepository {
	private final Log log = LogFactory.getLog(AuthenticationRepositoryImpl.class);
	
	public final Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

	@Override
	public Users findUserById(String id) throws RepositoryException {
		String sql = "select * from u_user where id = ?";
		Object[] params = {id};
		Users user = dbUtilsTemplate.findFirst(Users.class, sql, params);
		return user;
	}

	@Override
	public Users findUserByUserName(String username) throws RepositoryException {
		String sql = "select * from u_user where username = ?";
		Object[] params = {username};
		Users user = dbUtilsTemplate.findFirst(Users.class, sql, params);
		return user;
	}

	@Override
	public String findPasswordByUserName(String username) throws RepositoryException {
		Users user = this.findUserByUserName(username);
		return user.getPassword();
	}

	@Override
	public List<Role> findRoleByUserId(String userid) throws RepositoryException {
		List<Role> roleList = new ArrayList<Role>();
		
		List<UserRole> userRole_list = this.findUserRoleByUserId(userid);
		
		for(UserRole userRole : userRole_list) {
			String roleId = userRole.getRoleid();
			Object[] role_params = {roleId};
			String role_sql = "select * from u_role where id = ?";
			Role role = dbUtilsTemplate.findFirst(Role.class, role_sql, role_params);
			if(null != role) {
				roleList.add(role);
			}
		}
		
		return roleList;
	}
	
	@Override
	public List<Resource> findResourceById(String id) throws RepositoryException {
		String sql = "select * from u_resource where id = ?";
		Object[] params = {id};
		List<Resource> list = dbUtilsTemplate.find(Resource.class, sql, params);
		return list;
	}
	
	@Override
	public List<Resource> findResource() throws RepositoryException {
		String sql = "select * from u_resource";
		List<Resource> list = dbUtilsTemplate.find(Resource.class, sql);
		return list;
	}

	@Override
	public List<Resource> findEnabledResource() throws RepositoryException {
		String sql = "select * from u_resource where enabled = 1";
		List<Resource> list = dbUtilsTemplate.find(Resource.class, sql);
		return list;
	}

	@Override
	public List<Resource> findResourceByUserId(String userid) throws RepositoryException {
		List<Resource> list = new ArrayList<Resource>();
		
		List<Role> roleList = this.findRoleByUserId(userid);
		for(Role role : roleList) {
			List<Resource> resourceList = this.findResourceByRoleId(role.getId());
			list.addAll(resourceList);
		}
		
		return list;
	}

	@Override
	public List<Resource> findResourceByRoleId(String roleid) throws RepositoryException {
		List<Resource> list = new ArrayList<Resource>();
		
		List<RoleResource> roleResourceList = this.findRoleResourceByRoleId(roleid);
		for(RoleResource roleResource : roleResourceList) {
			List<Resource> _list = this.findResourceById(roleResource.getResourceid());
			if(CollectionUtils.isNotEmpty(_list)) {
				list.addAll(_list);
			}
		}
		
		return list;
	}

	@Override
	public List<UserRole> findUserRoleByUserId(String userid) throws RepositoryException {
		String sql = "select * from u_user_role where userid = ?";
		Object[] params = {userid};
		List<UserRole> list = dbUtilsTemplate.find(UserRole.class, sql, params);
		
		return list;
	}

	@Override
	public List<RoleResource> findRoleResourceByRoleId(String roleid) throws RepositoryException {
		String sql = "select * from u_role_resource where roleid = ?";
		Object[] params = {roleid};
		List<RoleResource> list = dbUtilsTemplate.find(RoleResource.class, sql, params);
		
		return list;
	}

	@Override
	public Map<String, Collection<ConfigAttribute>> getAllResource() throws RepositoryException {
		if(0 == resourceMap.size()) {
			this.reloadAllResource();
		}
		
		return resourceMap;
	}

	@Override
	public void reloadAllResource() throws RepositoryException {
		try {
			resourceMap.clear();
			List<Resource> resourceList = this.findEnabledResource();
			for(Resource resource : resourceList) {
				ConfigAttribute ca = new SecurityConfig(resource.getAccess());
				Collection<ConfigAttribute> value = new ArrayList<ConfigAttribute>();
				value.add(ca);
				resourceMap.put(resource.getResourceurl(), value);
			}
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
		}
	}

}
