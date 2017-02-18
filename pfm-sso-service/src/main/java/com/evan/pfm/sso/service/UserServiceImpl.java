package com.evan.pfm.sso.service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.evan.pfm.common.config.PropertiesConfig;
import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.common.exception.ErrorMessage;
import com.evan.pfm.common.service.BaseService;
import com.evan.pfm.common.util.Md5Util;
import com.evan.pfm.sso.dao.intf.UserCacheDAO;
import com.evan.pfm.sso.dao.intf.UserDAO;
import com.evan.pfm.sso.entity.User;
import com.evan.pfm.sso.service.intf.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserCacheDAO userCacheDAO;

	@Autowired
	PropertiesConfig propertiesConfig;

	public UserServiceImpl() {
		listenerAdapters.add(this);
	}

	@Override
	public String login(String username, String password) {
		// messageConfig.sendMessage("user", "用户消息");
		// this.sendMessage("role1", "角色消息");
		
		if (StringUtils.isEmpty(username))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_USERNAME);
		if (StringUtils.isEmpty(password))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_PASSWORD);

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
		if (StringUtils.isEmpty(token))
			throw new BusinessException(ErrorMessage.ERROR_001);
		User user = userCacheDAO.getUser(token);
		if (null == user || user.isEmpty())
			throw new BusinessException(ErrorMessage.ERROR_001);
		return user;
	}

	@Override
	public void register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone)  throws BusinessException{
		if (StringUtils.isEmpty(username))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_USERNAME);
		if (StringUtils.isEmpty(email))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_USER_EMAIL);
		if (StringUtils.isEmpty(password))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_PASSWORD);
		if (StringUtils.isEmpty(repeatedPassword))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_USER_REPEATED_PASSWORD);
		if (StringUtils.isEmpty(fullname))
			throw new BusinessException(ErrorMessage.ERROR_REQUIRED_FULL_NAME);

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

	@Override
	@Transactional
	public User uploadAvatar(String token, String fileName, byte[] fileBytes) {
		User user = this.getUserByToken(token);		
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (!"jpg".equals(suffix) && !"jpeg".equals(suffix) && !"png".equals(suffix) && !"gif".equals(suffix)) {
			throw new BusinessException(ErrorMessage.ERROR_003);
		}
		InputStream in = new ByteArrayInputStream(fileBytes);
		String filePath = String.format("%suser_avatar/%d.%s", propertiesConfig.getProperties().getProperty("img.disk.path"), user.getId(), suffix);
		String fileUrl = String.format("%suser_avatar/%d.%s", propertiesConfig.getProperties().getProperty("img.url"), user.getId(), suffix);
//		File destFile = new File(filePath);
		try {
			FileOutputStream out = new FileOutputStream(filePath);  
            int n = -1;  
            byte[] b = new byte[10240];  
            while((n=in.read(b)) != -1){  
                System.out.println(new String(b,0,n,"utf-8"));  
                out.write(b, 0, n);  
            }  
            out.close();  
            out.flush();  
            in.close();  
            
			this.userCacheDAO.updateUser(user);
			user.setAvatar(fileUrl);
			this.userDAO.updateUser(user);
			return user;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorMessage.ERROR_000);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorMessage.ERROR_000);
		}
	}
}