package com.demo.subsystem.authentication.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.demo.subsystem.authentication.entity.Resource;
import com.demo.subsystem.authentication.entity.Role;
import com.demo.subsystem.authentication.entity.RoleResource;
import com.demo.subsystem.authentication.entity.UserRole;
import com.demo.subsystem.authentication.entity.Users;
import com.demo.subsystem.common.BaseRepository;
import com.demo.subsystem.common.RepositoryException;

public interface IAuthenticationRepository extends BaseRepository {

	public Users findUserById(String id) throws RepositoryException;
	
	public Users findUserByUserName(String username) throws RepositoryException;
	
	public String findPasswordByUserName(String username) throws RepositoryException;
	
	public List<Role> findRoleByUserId(String userid) throws RepositoryException;
	
	public List<Resource> findResourceById(String id) throws RepositoryException;

	public List<Resource> findResource() throws RepositoryException;

	public List<Resource> findEnabledResource() throws RepositoryException;

	public List<Resource> findResourceByUserId(String userid) throws RepositoryException;
	
	public List<Resource> findResourceByRoleId(String roleid) throws RepositoryException;
	
	public List<UserRole> findUserRoleByUserId(String userid) throws RepositoryException;
	
	public List<RoleResource> findRoleResourceByRoleId(String roleid) throws RepositoryException;
	
	public Map<String, Collection<ConfigAttribute>> getAllResource() throws RepositoryException;
	
	public void reloadAllResource() throws RepositoryException;
}
