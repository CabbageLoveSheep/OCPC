package com.violet.ocpc.web.dao.impl;

import java.util.List;

import com.violet.ocpc.web.dao.ProjectDao;
import com.violet.ocpc.web.dao.mapper.ProjectMapper;
import com.violet.ocpc.web.holder.ProjectHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HYJ
 *
 */
@Service("ProjectDao")
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<ProjectHolder> getProjectListByAnd(ProjectHolder projectHolder) {
		return projectMapper.getProjectListByAnd(projectHolder);
	}

	@Override
	public int insertProject(ProjectHolder projectHolder) {
		// TODO Auto-generated method stub
		return projectMapper.insertProject(projectHolder);
	}

}
