package com.evan.pfm.project.serivce;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evan.pfm.common.service.BaseService;
import com.evan.pfm.project.dao.intf.ProjectDAO;
import com.evan.pfm.project.entity.Project;
import com.evan.pfm.project.entity.Project.ProjectStatus;
import com.evan.pfm.service.intf.ProjectService;

@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	
	@Override
	public void addProject(String name, String description, Date planStartDate, Date planEndDate, Date actualStartDate, Date actualEndDate, String cover, Integer ownerId) {
		Project project = new Project();
		project.setName(name);
		project.setDescription(description);
		project.setPlanStartDate(planStartDate);
		project.setPlanEndDate(planEndDate);
		project.setActualStartDate(actualStartDate);
		project.setActualEndDate(actualEndDate);
		project.setStatus(ProjectStatus.New);
		project.setCover(cover);
		project.setCreatedTime(new Date());
		
		projectDAO.insert(project);
	}

	@Override
	public void getProjectByUser(Integer userId) {
		// TODO Auto-generated method stub

	}

}
