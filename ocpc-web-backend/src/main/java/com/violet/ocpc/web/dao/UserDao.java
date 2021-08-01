package com.violet.ocpc.web.dao;

import java.util.List;

import com.violet.ocpc.web.holder.UserHolder;

public interface UserDao {
	List<UserHolder> queryUserListByAnd(UserHolder userParam);
	
	int insertUser(UserHolder user);
	
	List<UserHolder> queryUserListByLogin(UserHolder loginUser);
}
