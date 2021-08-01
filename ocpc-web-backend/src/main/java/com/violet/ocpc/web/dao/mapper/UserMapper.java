package com.violet.ocpc.web.dao.mapper;

import java.util.List;

import com.violet.ocpc.web.holder.UserHolder;

public interface UserMapper {

	List<UserHolder> queryUserListByAnd(UserHolder userParam);
	
	int insertUser(UserHolder user);
	
	List<UserHolder> queryUserListByLogin(UserHolder loginUser);
}
