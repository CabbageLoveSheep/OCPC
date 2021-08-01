package com.violet.ocpc.web.service;

import java.math.BigDecimal;

import com.violet.ocpc.web.facade.response.UserSettingResponse;

/**
 * @author HYJ
 *
 */
public interface UserSettingService {
	int addUserSettingInitData(BigDecimal userOid) throws Exception;
	
	UserSettingResponse queryInitCreateProjectData(BigDecimal userOid) throws Exception;
}
