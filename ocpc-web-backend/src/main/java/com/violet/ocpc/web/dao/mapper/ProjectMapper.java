package com.violet.ocpc.web.dao.mapper;

import java.util.List;

import com.violet.ocpc.web.holder.ProjectHolder;

/**
 * @author HYJ
 *
 */
public interface ProjectMapper {
	List<ProjectHolder> getProjectListByAnd(ProjectHolder projectHolder);
	
	int insertProject(ProjectHolder projectHolder);
}
