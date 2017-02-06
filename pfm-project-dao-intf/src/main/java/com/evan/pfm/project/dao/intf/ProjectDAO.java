package com.evan.pfm.project.dao.intf;

import com.evan.pfm.project.entity.Project;

public interface ProjectDAO {
	void insert(Project project);
	void getById(Integer id);
	void getByUserId(Integer userId);
}
