package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.violet.ocpc.web.dao.EmailVerifyCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.violet.ocpc.web.holder.EmailVerifyCodeHolder;
import com.violet.ocpc.web.service.EmailVerifyCodeService;
import com.violet.ocpc.web.service.OidService;
/**
 * @author HYJ
 *
 */
@Service("EmailVerifyCodeService")
public class EmailVerifyCodeServiceImpl implements EmailVerifyCodeService{
	@Value("${email.active.time}")
	private int emailActiveTime; // 单位:分钟
	
	@Autowired
	private EmailVerifyCodeDao emailVerifyCodeDao;
	
	@Autowired
	private OidService oidService;

	@Override
	public boolean addEmailVerifyCode(EmailVerifyCodeHolder emailVerifyCode) throws Exception {
		BigDecimal oid = oidService.getOid();
		emailVerifyCode.setRecordOid(oid);
		
		if(emailVerifyCodeDao.insertEmailVerifyCode(emailVerifyCode) > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSameEmailCodeToDisable(EmailVerifyCodeHolder emailVerifyCode) throws Exception {
		if(emailVerifyCodeDao.updateSameEmailCodeToDisable(emailVerifyCode)>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyEmailCode(String email, String verifyCode) throws Exception {
		
		EmailVerifyCodeHolder emailVerifyCode = new EmailVerifyCodeHolder();
		emailVerifyCode.setEmail(email);
		emailVerifyCode.setVerifyCode(verifyCode);
		emailVerifyCode.setIsDisable("N");
		emailVerifyCode.setEffectiveTime(emailActiveTime);
		
		List<EmailVerifyCodeHolder> emailVerifyCodeList = emailVerifyCodeDao
				.verifyEmailCode(emailVerifyCode);
		if(emailVerifyCodeList != null && !emailVerifyCodeList.isEmpty())
		{
			return true;
		}
		
		return false;
	}

}
