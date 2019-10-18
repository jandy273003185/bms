package org.gyzb.platform.web.admin.user.dao;

import java.util.List;

import org.gyzb.platform.web.admin.user.bean.User;
import org.gyzb.platform.web.admin.user.mapper.UserMapper;
import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * dao层，一般分页需要
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserDAO.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Repository
public class UserDAO{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 分页查询用户列表
	 * @return
	 */
	@Page
	public List<User> selectUserListByPage(User user) {
		return userMapper.selectUserList(user);
	}
}


