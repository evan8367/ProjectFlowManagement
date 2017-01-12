package com.evan.pfm.sso.dao.intf;

import java.util.List;

import com.evan.pfm.sso.entity.User;

public interface UserDAO {
	void insertUser(User user);
	void deleteUser(Integer id);
	List<User> getAllUserList();
	User getUser(Integer id);
	List<User> getUserByUserNameAndPassword(String username, String password);
}
