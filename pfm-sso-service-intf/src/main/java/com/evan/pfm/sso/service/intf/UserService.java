package com.evan.pfm.sso.service.intf;

import java.util.List;

import com.evan.pfm.sso.entity.User;

public interface UserService {
	void register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone);
	String login(String username, String password);
	List<User> getAllUser();
	User getUserByToken(String token);
	void logout(String token);
}
