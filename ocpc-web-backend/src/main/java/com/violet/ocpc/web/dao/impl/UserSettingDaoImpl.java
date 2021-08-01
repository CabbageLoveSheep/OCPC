package com.violet.ocpc.web.dao.impl;

import java.math.BigDecimal;

import com.violet.ocpc.web.dao.UserSettingDao;
import com.violet.ocpc.web.dao.mapper.UserSettingMapper;
import com.violet.ocpc.web.holder.UserSettingHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HYJ
 *
 */
@Service("UserSettingDao")
public class UserSettingDaoImpl implements UserSettingDao {

	@Autowired
	private UserSettingMapper userSettingMapper;
	
	@Override
	public int insertUserSetting(UserSettingHolder userSetting) {
		return userSettingMapper.insertUserSetting(userSetting);
	}

	@Override
	public UserSettingHolder getUserSettingByOid(BigDecimal userOid) {
		return userSettingMapper.getUserSettingByOid(userOid);
	}

}
