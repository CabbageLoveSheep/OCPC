package com.violet.ocpc.web.dao.mapper;

import java.util.List;

import com.violet.ocpc.web.holder.ProjectFileHolder;

/**
 * @author HYJ
 *
 */
public interface ProjectFileMapper {
	int insertProjectFile(ProjectFileHolder projectFile);
	
	List<ProjectFileHolder> getProjectFilesByAnd(ProjectFileHolder projectFile);
}
