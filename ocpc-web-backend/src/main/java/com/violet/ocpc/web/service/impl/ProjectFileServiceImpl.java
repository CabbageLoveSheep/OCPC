package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.violet.ocpc.web.dao.ProjectFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.violet.ocpc.web.holder.ProjectFileHolder;
import com.violet.ocpc.web.service.OidService;
import com.violet.ocpc.web.service.ProjectFileService;

/**
 * @author HYJ
 *
 */
@Service("ProjectFileService")
public class ProjectFileServiceImpl implements ProjectFileService{

	@Autowired
	private ProjectFileDao projectFileDao;
	
	@Autowired
	private OidService oidService;
	
	@Override
	public void addProjectFile(ProjectFileHolder pFileHolder) throws Exception {
		// TODO Auto-generated method stub
		BigDecimal oid = oidService.getOid();
		pFileHolder.setFileOid(oid);
		int result = projectFileDao.insertProjectFile(pFileHolder);
		
		if(result <= 0) {
			throw new Exception("存储文件失败!");
		}
	}

	@Override
	public List<ProjectFileHolder> queryProjectFilesByProjOid(BigDecimal projectOid) throws Exception {
		ProjectFileHolder projFile = new ProjectFileHolder();
		projFile.setProjOid(projectOid);
		return projectFileDao.getProjectFilesByAnd(projFile);
	}
	
}
