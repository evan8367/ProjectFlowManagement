package com.evan.pfm.sso.service.intf;

import java.util.List;

import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.sso.entity.User;

public interface UserService {
	void register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone) throws BusinessException;
	String login(String username, String password) throws BusinessException;
	List<User> getAllUser() throws BusinessException;
	User getUserByToken(String token) throws  BusinessException;
	void logout(String token) throws BusinessException;
	User uploadAvatar(String token, String fileName, byte[] fileBytes) throws BusinessException;
}
