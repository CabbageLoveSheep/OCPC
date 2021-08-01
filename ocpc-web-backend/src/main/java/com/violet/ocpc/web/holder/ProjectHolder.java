package com.violet.ocpc.web.holder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HYJ
 *
 */
public class ProjectHolder extends BaseHolder {

	private BigDecimal projectOid;
	private BigDecimal userOid;
	private String projectName;
	private String projectDesc;
	private int fileCount;
	private String projMark;
	private List<ProjectFileHolder> projFileList;

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

	public String getProjMark() {
		return projMark;
	}

	public void setProjMark(String projMark) {
		this.projMark = projMark;
	}

	public List<ProjectFileHolder> getProjFileList() {
		return projFileList;
	}

	public void setProjFileList(List<ProjectFileHolder> projFileList) {
		this.projFileList = projFileList;
	}

}
