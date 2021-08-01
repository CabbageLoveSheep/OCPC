package com.violet.ocpc.web.dao;

import java.math.BigDecimal;

import com.violet.ocpc.web.holder.UserSettingHolder;

/**
 * @author HYJ
 *
 */
public interface UserSettingDao {
	int insertUserSetting(UserSettingHolder userSetting);
	
	UserSettingHolder getUserSettingByOid(BigDecimal userOid);
}
