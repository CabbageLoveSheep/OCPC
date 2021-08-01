package com.violet.ocpc.web.service;

import com.violet.ocpc.web.facade.request.UserRequest;
import com.violet.ocpc.web.facade.response.EmailResponse;
import com.violet.ocpc.web.holder.UserHolder;

public interface UserService {

	EmailResponse verifyEmail(String email) throws Exception;
	
	boolean sendVerifyCode(String email) throws Exception;

	String saveRegister(UserRequest userReq) throws Exception;
	
	UserHolder queryLoginUser(UserRequest userReq) throws Exception;
}
