package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.violet.ocpc.web.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.violet.ocpc.web.common.contants.Contants;
import com.violet.ocpc.web.common.util.EmailUtil;
import com.violet.ocpc.web.common.util.RadmonUtils;
import com.violet.ocpc.web.facade.request.UserRequest;
import com.violet.ocpc.web.facade.response.EmailResponse;
import com.violet.ocpc.web.holder.Email;
import com.violet.ocpc.web.holder.EmailVerifyCodeHolder;
import com.violet.ocpc.web.holder.UserHolder;
import com.violet.ocpc.web.service.EmailVerifyCodeService;
import com.violet.ocpc.web.service.OidService;
import com.violet.ocpc.web.service.UserService;
import com.violet.ocpc.web.service.UserSettingService;

@Service("UserService")
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final int NUMBER_SIX = 6;
	private static final String registerEmailContent = "<html><header><meta charset='utf-8'></header><body>"
            + "<h3>您好：</h3>"
            + "<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎注册使用MIA图像分析系统！您此次操作的校验码为：#verifyCode#" 
            + "，有效时间为30分钟！<br/>如非本人操作，请忽略！</div>"
            + "</body><ml>";
	
	
	@Autowired
    @Qualifier("adminEmail")
    private Email adminEmail;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSettingService userSettingService;
	
	@Autowired
	private OidService oidService;
	
	@Autowired
	private EmailVerifyCodeService emailVerifyCodeService;

	@Override
	public EmailResponse verifyEmail(String email) throws Exception {
		UserHolder user = new UserHolder();
		user.setEmail(email);
		List<UserHolder> userList = userDao.queryUserListByAnd(user);
		
		EmailResponse response = new EmailResponse();
//		response.setRetCode(Contants.RET_CODE_SUCCESS);
		if(userList == null || userList.isEmpty())
		{
			response.setIsExist(Contants.VALUE_NO);
		}
		else
		{
			response.setIsExist(Contants.VALUE_YES);
		}
		
		return response;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
    public boolean sendVerifyCode(String email) throws Exception
    {
		LOGGER.info("==> Email : " + email);
		
		String verifyCode = RadmonUtils.genDigitalPwd(NUMBER_SIX);
		String content = registerEmailContent.replace("#verifyCode#", verifyCode);
		String subject = "MIA注册";
		// 发送验证码
		EmailUtil.sendMail(adminEmail, subject, content, email);
		
		// 旧的验证码标记失效
		EmailVerifyCodeHolder emailVerifyCode = new EmailVerifyCodeHolder();
		emailVerifyCode.setEmail(email);
		boolean disableOldCode = emailVerifyCodeService.updateSameEmailCodeToDisable(emailVerifyCode);
		LOGGER.info("==> disableOldCode: " + disableOldCode);
		
		// 验证码信息入库
		emailVerifyCode = new EmailVerifyCodeHolder();
		emailVerifyCode.setEmail(email);
		emailVerifyCode.setVerifyCode(verifyCode);
		boolean isSuccess = emailVerifyCodeService.addEmailVerifyCode(emailVerifyCode);
		
        return isSuccess;
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveRegister(UserRequest userReq) throws Exception {
		String verifyMsg = null;
		
		String email = userReq.getEmail();
		// 校验邮箱
		UserHolder user = new UserHolder();
		user.setEmail(email);
		List<UserHolder> userList = userDao.queryUserListByAnd(user);
		if(userList != null && !userList.isEmpty())
		{
			verifyMsg = "用户邮箱已存在!";
			return verifyMsg;
		}
		
		// 校验验证码
		boolean verifyEmailRes = emailVerifyCodeService
				.verifyEmailCode(email, userReq.getVerifyCode());
		if(!verifyEmailRes)
		{
			verifyMsg = "验证码错误!";
			return verifyMsg;
		}
		
		BigDecimal userOid = oidService.getOid();
		user = new UserHolder();
		user.setUserOid(userOid);
		user.setUserName(userReq.getUserName());
		user.setLoginId(email);
		user.setLoginPwd(userReq.getLoginPwd());
		user.setGender(userReq.getGender());
		user.setMobile(userReq.getMobile());
		user.setEmail(email);
		user.setLevel(1);
		user.setFailAttempts(0);
		user.setDefMark(userOid.toString());
		user.setActive(Contants.VALUE_YES);
		user.setBlocked(Contants.VALUE_NO);
		
		int result = userDao.insertUser(user);
		if(result <= 0)
		{
			verifyMsg = "注册用户失败! 请联系管理员寻求帮助!";
			return verifyMsg;
		}
		
		result = userSettingService.addUserSettingInitData(userOid);
		if(result <= 0)
		{
			verifyMsg = "初始化用户设置失败!";
		}
		
		return verifyMsg;
	}

	@Override
	public UserHolder queryLoginUser(UserRequest userReq) throws Exception {
		UserHolder loginUser = new UserHolder();
		loginUser.setLoginId(userReq.getLoginId());
		loginUser.setLoginPwd(userReq.getLoginPwd());
		
		List<UserHolder> userList = userDao.queryUserListByLogin(loginUser);
		if(userList != null && !userList.isEmpty())
		{
			return userList.get(0);
		}
		
		return null;
	}
}
