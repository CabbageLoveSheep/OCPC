package com.violet.ocpc.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.violet.ocpc.web.holder.ProjectFileHolder;

/**
 * @author HYJ
 *
 */
public interface ProjectFileService {
	void addProjectFile(ProjectFileHolder pFileHolder) throws Exception;
	
	List<ProjectFileHolder> queryProjectFilesByProjOid(BigDecimal projectOid) throws Exception;
}
