package com.violet.ocpc.web.dao;

import java.util.List;

import com.violet.ocpc.web.holder.ProjectFileHolder;

/**
 * @author HYJ
 *
 */
public interface ProjectFileDao {
	int insertProjectFile(ProjectFileHolder projectFile);
	
	List<ProjectFileHolder> getProjectFilesByAnd(ProjectFileHolder projFileHolder);
}
