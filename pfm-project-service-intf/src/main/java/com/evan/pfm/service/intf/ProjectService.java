package com.evan.pfm.service.intf;

import java.util.Date;
import java.util.List;

import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.project.entity.Project;

public interface ProjectService {
	void addProject(String name, String description, Date planStartDate, Date planEndDate, Date actualStartDate, Date actualEndDate, String cover, Integer ownerId);
	List<Project> getProjectByUser(String userToken) throws BusinessException;
}
