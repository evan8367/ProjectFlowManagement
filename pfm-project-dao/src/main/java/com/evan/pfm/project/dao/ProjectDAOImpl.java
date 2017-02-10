package com.evan.pfm.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.evan.pfm.common.dal.BaseDAO;
import com.evan.pfm.project.dao.intf.ProjectDAO;
import com.evan.pfm.project.entity.Project;

@Repository
public class ProjectDAOImpl extends BaseDAO implements ProjectDAO {

	@Override
	public void insert(Project project) {
		String sql = this.getSql("insert");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", project.getName());
		param.put("description", project.getDescription());
		param.put("plan_start_date", project.getPlanStartDate());
		param.put("plan_end_date", project.getPlanEndDate());
		param.put("actual_start_date", project.getActualStartDate());
		param.put("actual_end_date", project.getActualEndDate());
		param.put("cover", project.getCover());
		param.put("status", project.getStatus());
		this.sqlHelper.update(sql, param);
	}

	@Override
	public void getById(Integer id) {
		String sql = this.getSql("select_by_id");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", param);
		this.sqlHelper.find(sql, param, Project.class);
	}

	@Override
	public List<Project> getByUserId(Integer userId) {
		String sql = this.getSql("select_by_user_id");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", param);
		return this.sqlHelper.findAll(sql, param, Project.class);
	}
}
