package com.violet.ocpc.web.holder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HYJ
 *
 */
public class UserSettingHolder {
	private BigDecimal userOid;
	private String runMode;
	private String isMsgNotify;
	private String calParamUnit;
	private int maxLimitUpload;
	private BigDecimal multipleX;

	private Date createDate;
	private Date updateDate;

	public BigDecimal getUserOid() {
		return userOid;
	}

	public void setUserOid(BigDecimal userOid) {
		this.userOid = userOid;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getIsMsgNotify() {
		return isMsgNotify;
	}

	public void setIsMsgNotify(String isMsgNotify) {
		this.isMsgNotify = isMsgNotify;
	}

	public String getCalParamUnit() {
		return calParamUnit;
	}

	public void setCalParamUnit(String calParamUnit) {
		this.calParamUnit = calParamUnit;
	}

	public int getMaxLimitUpload() {
		return maxLimitUpload;
	}

	public void setMaxLimitUpload(int maxLimitUpload) {
		this.maxLimitUpload = maxLimitUpload;
	}

	public BigDecimal getMultipleX() {
		return multipleX;
	}

	public void setMultipleX(BigDecimal multipleX) {
		this.multipleX = multipleX;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
