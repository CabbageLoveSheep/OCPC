package com.violet.ocpc.web.dao.mapper;

import java.math.BigDecimal;

import com.violet.ocpc.web.holder.UserSettingHolder;

/**
 * @author HYJ
 *
 */
public interface UserSettingMapper {
	int insertUserSetting(UserSettingHolder userSetting);
	
	UserSettingHolder getUserSettingByOid(BigDecimal userOid);
}
