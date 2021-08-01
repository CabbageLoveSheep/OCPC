package com.violet.ocpc.web.dao.impl;

import java.util.List;

import com.violet.ocpc.web.dao.UserDao;
import com.violet.ocpc.web.dao.mapper.UserMapper;
import com.violet.ocpc.web.holder.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HYJ
 *
 */
@Service("UserDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserHolder> queryUserListByAnd(UserHolder userParam) {
		
		return userMapper.queryUserListByAnd(userParam);
	}

	@Override
	public int insertUser(UserHolder user) {
		return userMapper.insertUser(user);
	}

	@Override
	public List<UserHolder> queryUserListByLogin(UserHolder loginUser) {
		return userMapper.queryUserListByLogin(loginUser);
	}
}
