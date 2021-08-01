package com.violet.ocpc.web.facade.response;

import java.math.BigDecimal;

import com.violet.ocpc.web.common.dto.BaseResponseDto;

/**
 * @author HYJ
 *
 */
public class ProjectFileResponse extends BaseResponseDto {
	private BigDecimal fileOid;
	private BigDecimal projectOid;
	private String filename;
	private String newFilename;
	private String filePath;

	public BigDecimal getFileOid() {
		return fileOid;
	}

	public void setFileOid(BigDecimal fileOid) {
		this.fileOid = fileOid;
	}

	public BigDecimal getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(BigDecimal projectOid) {
		this.projectOid = projectOid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
