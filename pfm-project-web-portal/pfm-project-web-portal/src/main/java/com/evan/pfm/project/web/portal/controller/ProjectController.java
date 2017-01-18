package com.evan.pfm.project.web.portal.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.evan.pfm.common.controller.BaseController;

@RequestMapping(value="project/project/", produces="application/json; charset=UTF-8")
public class ProjectController  extends BaseController{
	@RequestMapping("create")
	public String createProject() {
		
		return "";
	}
}
