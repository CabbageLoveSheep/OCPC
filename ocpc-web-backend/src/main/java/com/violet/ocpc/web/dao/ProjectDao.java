package com.violet.ocpc.web.dao;

import java.util.List;

import com.violet.ocpc.web.holder.ProjectHolder;

/**
 * @author HYJ
 *
 */
public interface ProjectDao {
	List<ProjectHolder> getProjectListByAnd(ProjectHolder projectHolder);
	
	int insertProject(ProjectHolder projectHolder);
	
}
