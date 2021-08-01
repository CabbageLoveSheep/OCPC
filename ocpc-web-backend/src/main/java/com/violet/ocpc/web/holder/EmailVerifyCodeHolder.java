package com.violet.ocpc.web.holder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HYJ
 *
 */
public class EmailVerifyCodeHolder {
	private BigDecimal recordOid;
	
	private String email;
	
	private String verifyCode;
	
	private int effectiveTime;// min分钟
	
	private String isDisable;
	
	private Date createDate;

	public BigDecimal getRecordOid() {
		return recordOid;
	}

	public void setRecordOid(BigDecimal recordOid) {
		this.recordOid = recordOid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
