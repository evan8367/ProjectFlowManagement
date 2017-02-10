package com.evan.pfm.sso.service.intf;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.sso.entity.User;

public interface UserService {
	void register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone) throws BusinessException;
	String login(String username, String password) throws BusinessException;
	List<User> getAllUser() throws BusinessException;
	User getUserByToken(String token) throws  BusinessException;
	void logout(String token) throws BusinessException;
	User uploadAvatar(MultipartFile file, String token) throws BusinessException;
}
