package com.violet.ocpc.web.facade.response;

import java.math.BigDecimal;

import com.violet.ocpc.web.common.dto.BaseResponseDto;

/**
 * @author HYJ
 *
 */
public class ProjectResponse extends BaseResponseDto {
	private static final long serialVersionUID = 5588027231029268903L;
	
	private BigDecimal projectOid;
	private BigDecimal userOid;
	private String projectName;
	private String projectDesc;
	private int fileCount;
	private String createDate;// YYYY-MM-dd HH:mm:ss
	
	private String isExist;

	
	
	public BigDecimal getUserOid() {
		return userOid;
	}

	public void setUserOid(BigDecimal userOid) {
		this.userOid = userOid;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public BigDecimal getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(BigDecimal projectOid) {
		this.projectOid = projectOid;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
	
}
