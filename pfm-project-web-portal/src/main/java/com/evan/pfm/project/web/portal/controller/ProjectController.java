package com.evan.pfm.project.web.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evan.pfm.common.controller.BaseController;
import com.evan.pfm.common.controller.ResultDTO;
import com.evan.pfm.common.util.CookieUtils;
import com.evan.pfm.project.entity.Project;
import com.evan.pfm.service.intf.ProjectService;

@RestController
@RequestMapping(value="project/project/", produces="application/json; charset=UTF-8")
public class ProjectController  extends BaseController{
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping("create")
	public String createProject(String projectName,  String purposedStartDate, String purposedEndDate, String description, String cover) {
		ResultDTO resultDTO = new ResultDTO();
		return resultDTO.toString();
	}
	
	@RequestMapping("getProjectList")
	public String getProjectList() {
		String token = CookieUtils.getCookie(USER_TOKEN_NAME);
		List<Project> projectList = this.projectService.getProjectByUser(token);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("projectList", projectList);
		return resultDTO.toString();
	}
}
