package com.evan.pfm.sso.dao.intf;

import com.evan.pfm.sso.entity.User;

public interface UserCacheDAO {
	void  deleteUser(Integer id);
	void deleteUser(String token);
	void updateUser(User user);
	String insertUser(User user);
	User getUser(String token);
}