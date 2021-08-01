package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.violet.ocpc.web.dao.UserSettingDao;
import com.violet.ocpc.web.facade.response.UserSettingResponse;
import com.violet.ocpc.web.holder.UserSettingHolder;
import com.violet.ocpc.web.service.UserSettingService;

/**
 * @author HYJ
 *
 */
@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserSettingServiceImpl.class);
	
	@Autowired
	private UserSettingDao userSettingDao;
	
	@Override
	public int addUserSettingInitData(BigDecimal userOid) throws Exception {
		UserSettingHolder userSetting = initUserSetting(userOid);
		return userSettingDao.insertUserSetting(userSetting);
	}
	
	private UserSettingHolder initUserSetting(BigDecimal userOid) throws Exception {
		UserSettingHolder userSetting = new UserSettingHolder();
		userSetting.setUserOid(userOid);
		userSetting.setRunMode("S");
		userSetting.setIsMsgNotify("Y");
		userSetting.setMaxLimitUpload(2);
		userSetting.setCalParamUnit("T");
		userSetting.setMultipleX(new BigDecimal(6));
		
		return userSetting;
	}

	@Override
	public UserSettingResponse queryInitCreateProjectData(BigDecimal userOid) throws Exception {
		UserSettingResponse userSettingRes = new UserSettingResponse();
		
		UserSettingHolder userSetting = userSettingDao.getUserSettingByOid(userOid);
		
		BeanUtils.copyProperties(userSetting, userSettingRes);
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("==> queryInitCreateProjectData: " + JSONObject.toJSONString(userSettingRes));
		}
		
		return userSettingRes;
	}

}
