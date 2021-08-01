package com.violet.ocpc.web.dao.impl;

import java.util.List;

import com.violet.ocpc.web.dao.EmailVerifyCodeDao;
import com.violet.ocpc.web.dao.mapper.EmailVerifyCodeMapper;
import com.violet.ocpc.web.holder.EmailVerifyCodeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HUYJ
 *
 */
@Service("EmailVerifyCodeDao")
public class EmailVerifyCodeDaoImpl implements EmailVerifyCodeDao {
	@Autowired
	private EmailVerifyCodeMapper emailVerifyCodeMapper;
	
	@Override
	public int insertEmailVerifyCode(EmailVerifyCodeHolder emailVerifyCode) {
		return emailVerifyCodeMapper.insertEmailVerifyCode(emailVerifyCode);
	}

	@Override
	public int updateSameEmailCodeToDisable(EmailVerifyCodeHolder emailVerifyCode) {
		return emailVerifyCodeMapper.updateSameEmailCodeToDisable(emailVerifyCode);
	}

	@Override
	public List<EmailVerifyCodeHolder> verifyEmailCode(EmailVerifyCodeHolder emailVerifyCode) {
		return emailVerifyCodeMapper.verifyEmailCode(emailVerifyCode);
	}

}
