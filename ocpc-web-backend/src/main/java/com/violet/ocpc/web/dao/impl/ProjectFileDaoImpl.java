package com.violet.ocpc.web.dao.impl;

import java.util.List;

import com.violet.ocpc.web.dao.ProjectFileDao;
import com.violet.ocpc.web.dao.mapper.ProjectFileMapper;
import com.violet.ocpc.web.holder.ProjectFileHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HYJ
 *
 */
@Service("ProjectFileDao")
public class ProjectFileDaoImpl implements ProjectFileDao {

	@Autowired
	private ProjectFileMapper projectFileMapper;
	
	@Override
	public int insertProjectFile(ProjectFileHolder projectFile) {
		return projectFileMapper.insertProjectFile(projectFile);
	}

	@Override
	public List<ProjectFileHolder> getProjectFilesByAnd(ProjectFileHolder projFileHolder) {
		return projectFileMapper.getProjectFilesByAnd(projFileHolder);
	}

}
