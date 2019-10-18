package org.gyzb.platform.web.admin.userRole.service;

import java.util.List;

import org.gyzb.platform.web.admin.role.bean.Role;
import org.gyzb.platform.web.admin.userRole.bean.UserRole;
import org.gyzb.platform.web.admin.userRole.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserRoleService.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	/**
	 * 添加用户角色
	 * @param userRole
	 */
	public void insertUserRole(UserRole userRole){
		if(null == userRole){
			throw new IllegalArgumentException("用户角色对象为空");
		}
		
		userRoleMapper.insertUserRole(userRole);
	}
	
	/**
	 * 查找用户角色
	 * @param userId
	 * @return
	 */
	public List<Role> selectUserRole(int userId){
		
		return userRoleMapper.selectUserRoleByUserId(userId);
	}
	
	/**
	 * 更改用户角色
	 * @param userRole
	 */
	public void updateUserRole(UserRole userRole){
		userRoleMapper.updateUserRole(userRole);
	}

	public void deleteUserRole(int userId) {
		userRoleMapper.deleteUserRole(userId);
		
	}
}
