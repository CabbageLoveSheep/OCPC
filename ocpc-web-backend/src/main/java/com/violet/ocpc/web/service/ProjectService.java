package com.violet.ocpc.web.service;

import java.math.BigDecimal;

import com.violet.ocpc.web.facade.request.ProjectRequest;
import com.violet.ocpc.web.facade.response.ProjectQueryResponse;
import com.violet.ocpc.web.facade.response.ProjectResponse;

public interface ProjectService {
	ProjectResponse verifyProjectName(String projectName) throws Exception;
	
	ProjectResponse addSaveProject(ProjectRequest projectRequest) throws Exception;
	
	ProjectQueryResponse queryProjectList(ProjectRequest projectRequest) throws Exception;
	
	ProjectQueryResponse queryProjectInfo(BigDecimal projectOid) throws Exception;
}
