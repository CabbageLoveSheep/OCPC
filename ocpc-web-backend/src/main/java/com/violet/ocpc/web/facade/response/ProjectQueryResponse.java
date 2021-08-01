package com.violet.ocpc.web.facade.response;

import java.util.List;

import com.violet.ocpc.web.common.dto.BaseResponseDto;
import com.violet.ocpc.web.holder.ProjectFileHolder;
import com.violet.ocpc.web.holder.ProjectHolder;

/**
 * @author HYJ
 *
 */
public class ProjectQueryResponse extends BaseResponseDto {
	private List<ProjectHolder> list;
	
	private String projectName;
	private String projectDesc;
	private List<ProjectFileHolder> proFileList;

	public List<ProjectHolder> getList() {
		return list;
	}

	public void setList(List<ProjectHolder> list) {
		this.list = list;
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

	public List<ProjectFileHolder> getProFileList() {
		return proFileList;
	}

	public void setProFileList(List<ProjectFileHolder> proFileList) {
		this.proFileList = proFileList;
	}
	
}
