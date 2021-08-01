package com.violet.ocpc.web.facade.response.vo;

import java.math.BigDecimal;

public class ProjectVO {
	private BigDecimal projectOid;
	private BigDecimal userOid;
	private String projectName;
	private String projectDesc;
	private int fileCount;
	private String createDate;

	public BigDecimal getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(BigDecimal projectOid) {
		this.projectOid = projectOid;
	}

	public BigDecimal getUserOid() {
		return userOid;
	}

	public void setUserOid(BigDecimal userOid) {
		this.userOid = userOid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
