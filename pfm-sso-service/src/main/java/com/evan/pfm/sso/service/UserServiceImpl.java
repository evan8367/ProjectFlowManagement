package com.evan.pfm.sso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.common.exception.ErrorMessage;
import com.evan.pfm.common.exception.InvalidArgumentException;
import com.evan.pfm.common.exception.InvalidArgumentException.ErrorType;
import com.evan.pfm.common.service.BaseService;
import com.evan.pfm.common.util.Md5Util;
import com.evan.pfm.sso.dao.intf.UserCacheDAO;
import com.evan.pfm.sso.dao.intf.UserDAO;
import com.evan.pfm.sso.entity.User;
import com.evan.pfm.sso.service.config.MessageConfig;
import com.evan.pfm.sso.service.intf.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserCacheDAO userCacheDAO;

	@Autowired
	MessageConfig messageConfig;

	public UserServiceImpl() {
		listenerAdapters.add(this);
	}

	@Override
	public String login(String username, String password)  {
		// messageConfig.sendMessage("user", "用户消息");
		// this.sendMessage("role1", "角色消息");
		
		if(StringUtils.isEmpty(username))
			throw new InvalidArgumentException("User name", ErrorType.RequiredType);
		if(StringUtils.isEmpty(password))
			throw new InvalidArgumentException("Password", ErrorType.RequiredType);
		
		String userToken = new String();
		List<User> userList = this.userDAO.getUserByUserNameAndPassword(username, Md5Util.MD5(password));
		Boolean isExisting = !userList.isEmpty();
		
		if (!isExisting) {
			throw new BusinessException(ErrorMessage.ERROR_002);
		} 
		
		User user = userList.get(0);
		this.kickUser(user.getId());
		
		userToken = userCacheDAO.insertUser(user);
		user.setToken(userToken);
		return userToken;
	}

	@Override
	public List<User> getAllUser() {
		return this.userDAO.getAllUserList();
	}

	public void receiveMessage(String message) {
		System.out.println(message);
	}
	
	private void kickUser(Integer id) {
		userCacheDAO.deleteUser(id);
	}

	@Override
	public User getUserByToken(String token) {
		if(StringUtils.isEmpty(token))
			throw new BusinessException(ErrorMessage.ERROR_001);
		User user = userCacheDAO.getUser(token);
		if(null == user) 
			throw new BusinessException(ErrorMessage.ERROR_001);
		return user;
	}

	@Override
	public void register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone) {
		if(StringUtils.isEmpty(username))
			throw new InvalidArgumentException("User name", ErrorType.RequiredType);
		if(StringUtils.isEmpty(email))
			throw new InvalidArgumentException("Email", ErrorType.RequiredType);
		if(StringUtils.isEmpty(password))
			throw new InvalidArgumentException("Password", ErrorType.RequiredType);
		if(StringUtils.isEmpty(repeatedPassword))
			throw new InvalidArgumentException("Repeated Password", ErrorType.RequiredType);
		if(StringUtils.isEmpty(fullname))
			throw new InvalidArgumentException("Full name", ErrorType.RequiredType);
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(Md5Util.MD5(password));
		user.setCreateTime(new Date());
		user.setFullname(fullname);
		user.setMobilePhone(mobilePhone);
		
		this.userDAO.insertUser(user);
	}

	@Override
	public void logout(String token) {
		this.userCacheDAO.deleteUser(token);
	}
}