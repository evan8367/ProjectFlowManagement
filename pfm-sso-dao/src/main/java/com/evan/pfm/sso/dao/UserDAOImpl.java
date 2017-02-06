package com.evan.pfm.sso.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.evan.pfm.common.dal.BaseDAO;
import com.evan.pfm.sso.dao.intf.UserDAO;
import com.evan.pfm.sso.entity.User;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {
	

	@Override
	public void insertUser(User user) {

		String sql = this.getSql("insert");
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("company_id", user.getCompanyId());
		param.put("position_id", user.getPositionId());
		param.put("username", user.getUsername());
		param.put("email", user.getEmail());
		param.put("password", user.getPassword());
		param.put("create_time", user.getCreateTime());
		param.put("fullname", user.getFullname());
		param.put("description", user.getDescription());
		param.put("avatar", user.getAvatar());
		param.put("mobile_phone", user.getMobilePhone());

		this.sqlHelper.update(sql, param);
	}
	
	public void updateUser(User user) {
		String sql = this.getSql("update");
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("id", user.getId());
		param.put("company_id", user.getCompanyId());
		param.put("position_id", user.getPositionId());
		param.put("username", user.getUsername());
		param.put("email", user.getEmail());
		param.put("password", user.getPassword());
		param.put("create_time", user.getCreateTime());
		param.put("fullname", user.getFullname());
		param.put("description", user.getDescription());
		param.put("avatar", user.getAvatar());
		param.put("mobile_phone", user.getMobilePhone());
		
		this.sqlHelper.update(sql, param);
	}

	@Override
	public void deleteUser(Integer id) {
		String sql = this.getSql("insert");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);

		this.sqlHelper.update(sql, param);
	}

	@Override
	public List<User> getAllUserList() {
		String sql = this.getSql("select_all");
		return this.sqlHelper.findAll(sql, null, User.class);
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserByUserNameAndPassword(String username, String password) {
		String sql = this.getSql("select_by_username_password");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("password", password);

		return this.sqlHelper.findAll(sql, param, User.class);
	}
}
