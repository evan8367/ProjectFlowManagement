package com.evan.pfm.project.serivce;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evan.pfm.common.service.BaseService;
import com.evan.pfm.project.dao.intf.ProjectDAO;
import com.evan.pfm.project.entity.Project;
import com.evan.pfm.service.intf.ProjectService;
import com.evan.pfm.sso.entity.User;
import com.evan.pfm.sso.service.intf.UserService;

@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {

	@Autowired
	UserService userService;
	
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
		project.setStatus(Project.projectStatusNew);
		project.setCover(cover);
		project.setCreatedTime(new Date());
		
		projectDAO.insert(project);
	}

	@Override
	public List<Project> getProjectByUser(String userToken) {
		User user = userService.getUserByToken(userToken);
		return this.projectDAO.getByUserId(user.getId());
	}

}
