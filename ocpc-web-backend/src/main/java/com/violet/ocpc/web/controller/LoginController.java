package com.violet.ocpc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.violet.ocpc.web.common.contants.Contants;
import com.violet.ocpc.web.common.contants.ExceptionConstants;
import com.violet.ocpc.web.common.dto.BaseResponseDto;
import com.violet.ocpc.web.facade.request.UserRequest;
import com.violet.ocpc.web.facade.response.LoginResponse;
import com.violet.ocpc.web.facade.response.vo.UserVO;
import com.violet.ocpc.web.holder.UserHolder;
import com.violet.ocpc.web.service.UserService;

/**
 * @author HYJ
 *
 */
@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	private static final String SESSION_USER_LOGIN_ID = "SESSION_USER_LOGIN_ID";
	private static final String SESSION_USER_OID = "SESSION_USER_OID";
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/verify")
	public BaseResponseDto verifyLogin(HttpServletRequest req, String loginId, String loginPwd)
	{
		HttpSession session = req.getSession();
		session.removeAttribute(SESSION_USER_OID);
		session.removeAttribute(SESSION_USER_LOGIN_ID);
		
		LoginResponse res = null;
		try {
			UserRequest userReq = new UserRequest();
			userReq.setLoginId(loginId);
			userReq.setLoginPwd(loginPwd);
			this.printRequest(userReq);
			
			res = new LoginResponse();
			UserHolder currentUser = userService.queryLoginUser(userReq);
			
			res.setRetCode(SUCCESS);
			if(currentUser == null)
			{
				res.setRetMsg("账号或密码错误!");
				res.setValid(Contants.VALUE_NO);
				
				// 记录失败 +1 次
				
				return res;
			}
			
			if(Contants.VALUE_NO.equals(currentUser.getActive()))
			{
				res.setRetMsg("账号已失效!");
				res.setValid(Contants.VALUE_NO);
				return res;
			}
			
			if(Contants.VALUE_YES.equals(currentUser.getBlocked()))
			{
				res.setRetMsg("账号已被锁!");
				res.setValid(Contants.VALUE_NO);
				return res;
			}
			
			session.setAttribute(SESSION_USER_OID, currentUser.getUserOid().toString());
			session.setAttribute(SESSION_USER_LOGIN_ID, currentUser.getLoginId());
			
			res.setValid(Contants.VALUE_YES);
			res.setUserOid(currentUser.getUserOid().toString());
			UserVO userProfile = queryUserVO(currentUser);
			res.setUserProfile(userProfile);
			
		} catch (Exception e) {
			LOGGER.error("!!==> verifyLogin: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		
		return res;
	}
	
	private UserVO queryUserVO(UserHolder currentUser) throws Exception
	{
		UserVO userProfile = new UserVO();
		userProfile.setUserOid(currentUser.getUserOid());
		userProfile.setLoginId(currentUser.getLoginId());
		userProfile.setEmail(currentUser.getEmail());
		userProfile.setMobile(currentUser.getMobile());
		userProfile.setUserName(currentUser.getUserName());
		userProfile.setLevel(currentUser.getLevel());
		userProfile.setActive(currentUser.getActive());
		userProfile.setBlocked(currentUser.getBlocked());
		userProfile.setGENDER(currentUser.getGender());
		
		return userProfile;
	}
}
