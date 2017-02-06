package com.evan.pfm.service.intf;

import java.util.Date;

public interface ProjectService {
	void addProject(String name, String description, Date planStartDate, Date planEndDate, Date actualStartDate, Date actualEndDate, String cover, Integer ownerId);
	void getProjectByUser(Integer userId);
}
