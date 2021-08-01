package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.violet.ocpc.web.dao.ProjectDao;
import com.violet.ocpc.web.service.ProjectFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.violet.ocpc.web.common.contants.Contants;
import com.violet.ocpc.web.facade.request.ProjectRequest;
import com.violet.ocpc.web.facade.response.ProjectQueryResponse;
import com.violet.ocpc.web.facade.response.ProjectResponse;
import com.violet.ocpc.web.holder.ProjectFileHolder;
import com.violet.ocpc.web.holder.ProjectHolder;
import com.violet.ocpc.web.service.OidService;
import com.violet.ocpc.web.service.ProjectService;

/**
 * @author HYJ
 *
 */
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private OidService oidService;
	
	@Autowired
	private ProjectFileService projectFileService;
	
	@Override
	public ProjectResponse verifyProjectName(String projectName) throws Exception {
		ProjectResponse projRes = new ProjectResponse();
		try {
			ProjectHolder projHolder = new ProjectHolder();
			projHolder.setProjectName(projectName);
			List<ProjectHolder> projList = projectDao.getProjectListByAnd(projHolder);
			
			projRes.setRetCode(Contants.RET_CODE_SUCCESS);
			if(projList == null || projList.isEmpty()) 
			{
				projRes.setIsExist(Contants.VALUE_NO);
			}
			else
			{
				projRes.setIsExist(Contants.VALUE_YES);
			}
		} catch (Exception e) {
			projRes.setRetCode("999999");
			projRes.setRetMsg(e.getMessage());
			return projRes;
		}
		return projRes;
	}

	@Override
	public ProjectResponse addSaveProject(ProjectRequest projectReq) throws Exception {
		ProjectResponse projectRes = new ProjectResponse();
		
		ProjectHolder projectHolder = new ProjectHolder();
		BigDecimal oid = oidService.getOid();
		projectHolder.setProjectOid(oid);
		projectHolder.setProjectName(projectReq.getProjectName());
		projectHolder.setProjectDesc(projectReq.getProjectDesc());
		projectHolder.setUserOid(projectReq.getUserOid());
		projectHolder.setFileCount(projectReq.getFileCount());
		projectHolder.setProjMark(oid.toString());
		int result = projectDao.insertProject(projectHolder);
		if(result > 0) 
		{
			projectRes.setRetCode(Contants.RET_CODE_SUCCESS);
			projectRes.setProjectOid(oid);
		} 
		else 
		{
			projectRes.setRetCode(Contants.SAVE_ERROR);
			projectRes.setRetMsg("保存失败!");
		}
		return projectRes;
	}

	@Override
	public ProjectQueryResponse queryProjectList(ProjectRequest projectRequest) throws Exception {
		ProjectQueryResponse projQueryRes = new ProjectQueryResponse();
		try {
			ProjectHolder projectHolder = new ProjectHolder();
			projectHolder.setUserOid(projectRequest.getUserOid());
			projectHolder.setProjectName(StringUtils.trimToNull(projectRequest.getProjectName()));
			
			List<ProjectHolder> projList = projectDao.getProjectListByAnd(projectHolder);
			
			projQueryRes.setRetCode(Contants.RET_CODE_SUCCESS);
			projQueryRes.setList(projList);
		} catch (Exception e) {
			projQueryRes.setRetCode(Contants.QUERY_ERROR);
			projQueryRes.setRetMsg(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return projQueryRes;
	}

	@Override
	public ProjectQueryResponse queryProjectInfo(BigDecimal projectOid) throws Exception {
		ProjectQueryResponse proQueryRes = new ProjectQueryResponse();
		try {
			//查询Project
			ProjectHolder projectHolder = new ProjectHolder();
			projectHolder.setProjectOid(projectOid);
			List<ProjectHolder> projectList = projectDao.getProjectListByAnd(projectHolder);
			if(projectList == null || projectList.isEmpty())
			{
				proQueryRes.setRetCode(Contants.QUERY_ERROR);
				proQueryRes.setRetMsg("工程不存在!");
			}
			else
			{
				ProjectHolder projHolder = projectList.get(0);
				
				proQueryRes.setProjectName(projHolder.getProjectName());
				proQueryRes.setProjectDesc(projHolder.getProjectDesc());
				
				List<ProjectFileHolder> projectFiles = projectFileService
						.queryProjectFilesByProjOid(projectOid);
				proQueryRes.setProFileList(projectFiles);
			}
		} catch (Exception e) {
			proQueryRes.setRetCode(Contants.QUERY_ERROR);
			proQueryRes.setRetMsg(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return proQueryRes;
	}

}
