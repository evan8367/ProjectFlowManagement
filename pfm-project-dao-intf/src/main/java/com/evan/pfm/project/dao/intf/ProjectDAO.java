package com.evan.pfm.project.dao.intf;

import java.util.List;

import com.evan.pfm.project.entity.Project;

public interface ProjectDAO {
	void insert(Project project);
	void getById(Integer id);
	List<Project> getByUserId(Integer userId);
}
