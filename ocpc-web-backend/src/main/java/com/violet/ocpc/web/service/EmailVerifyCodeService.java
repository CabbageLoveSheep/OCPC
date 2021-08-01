package com.violet.ocpc.web.service;

import com.violet.ocpc.web.holder.EmailVerifyCodeHolder;

public interface EmailVerifyCodeService {
	boolean addEmailVerifyCode(EmailVerifyCodeHolder emailVerifyCode) throws Exception;
	
	boolean updateSameEmailCodeToDisable(EmailVerifyCodeHolder emailVerifyCode) throws Exception;
	
	boolean verifyEmailCode(String email, String verifyCode) throws Exception;
}
