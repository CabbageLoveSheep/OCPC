package com.violet.ocpc.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.violet.ocpc.web.common.contants.ExceptionConstants;
import com.violet.ocpc.web.common.dto.BaseResponseDto;
import com.violet.ocpc.web.facade.request.UserRequest;
import com.violet.ocpc.web.service.UserService;

/**
 * @author HYJ
 *
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/verifyEmail")
	public BaseResponseDto verifyEmail(String email)
	{
		
		try {
			BaseResponseDto baseRes = new BaseResponseDto();
			baseRes = userService.verifyEmail(email);
			baseRes.setRetCode(SUCCESS);
			LOGGER.info(JSONObject.toJSONString(baseRes));
			
			return baseRes;
		} catch (Exception e) {
			LOGGER.error("!!==> verifyEmail: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
	}

	@PostMapping(value="/sendCode")
	public BaseResponseDto sendCode(String email) 
	{
	    BaseResponseDto baseRes = null;
	    try
        {
	        baseRes = new BaseResponseDto();
	        
	        boolean sendSuccess = userService.sendVerifyCode(email);
	        if(sendSuccess)
	        {
	            baseRes.setRetCode(SUCCESS); 
	        }
	        else
	        {
	            baseRes.setRetCode(Error);
	            baseRes.setRetCode(Error_MSG);
	        }
	        
        }
        catch (Exception e)
        {
        	LOGGER.error("!!==> sendCode: " + e.getMessage());
            return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
        }
	    return baseRes;
	}
	

	@PostMapping(value="/save")
	public BaseResponseDto saveRegister(UserRequest registerReq)
	{
		BaseResponseDto baseRes = null;
		try {
			this.printRequest(registerReq);
			
			baseRes = new BaseResponseDto();
			String verifyMsg = userService.saveRegister(registerReq);
			if(StringUtils.isBlank(verifyMsg))
			{
				baseRes.setRetCode(SUCCESS);
				baseRes.setRetMsg("注册用户成功!");
			}
			else 
			{
				baseRes.setRetCode(SAVE_ERROR);
				baseRes.setRetMsg(verifyMsg);
			}
			
		} catch (Exception e) {
			LOGGER.error("!!==> saveRegister: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		return baseRes;
	}
}
