package com.violet.ocpc.web.dao.mapper;

import java.util.List;

import com.violet.ocpc.web.holder.EmailVerifyCodeHolder;

public interface EmailVerifyCodeMapper {
	int insertEmailVerifyCode(EmailVerifyCodeHolder emailVerifyCode);
	
	int updateSameEmailCodeToDisable(EmailVerifyCodeHolder emailVerifyCode);
	
	List<EmailVerifyCodeHolder> verifyEmailCode(EmailVerifyCodeHolder emailVerifyCode);
}
