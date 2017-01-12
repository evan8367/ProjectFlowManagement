package com.evan.pfm.sso.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.evan.pfm.sso.entity.User;
import com.evan.pfm.sso.service.intf.UserService;

public class UserServiceTest {
	@Autowired
	UserService userService;
	
//	@Test
	public void addUserTest() {
//		this.userService.addUser("evan", "evan8367@live.com", "qwe123QWE", new Date(), null);
	}
	
//	@Test
	public void loginTest() {
		Assert.assertTrue(!this.userService.login("evan", "qwe123QWE").isEmpty());
	}
	
//	@Test
	public void getAllUserTest() {
		Assert.assertFalse(this.userService.getAllUser().isEmpty());
	}
	
	@Test
	public void getUserByTokenTest() {
		User user = userService.getUserByToken("aa");
		Assert.assertNotNull(user);
	}

	@BeforeClass
	public void beforeClass() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
		userService = (UserService) context.getBean(UserService.class);
		context.close();
	}

}
