package com.evan.pfm.sso.web.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.evan.pfm.common.controller.BaseController;
import com.evan.pfm.common.controller.ResultDTO;
import com.evan.pfm.common.util.CookieUtils;
import com.evan.pfm.sso.entity.User;
import com.evan.pfm.sso.service.intf.UserService;

@RestController
@RequestMapping(value = "user/", produces = "application/json; charset=UTF-8")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("login")
	public String login(String username, String password, HttpServletResponse response) {
		ResultDTO resultDTO = new ResultDTO();
		String userToken = userService.login(username, password);
		if (!userToken.isEmpty()) {
			CookieUtils.setCookie(response, USER_TOKEN_NAME, userToken);
		}
		return resultDTO.toString();
	}

	@RequestMapping("getUserInfo")
	public String getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		ResultDTO resultDTO = new ResultDTO();
		String token = CookieUtils.getCookie(request, USER_TOKEN_NAME);

		if (!StringUtils.isEmpty(token))
			CookieUtils.setCookie(response, USER_TOKEN_NAME, token);

		User user = userService.getUserByToken(token);

		if (null != user) {
			resultDTO.addAttribute("userInfo", user);
		}

		return resultDTO.toString();
	}

	@RequestMapping("register")
	public String register(String username, String email, String password, String repeatedPassword, String fullname, String mobilePhone) {

		ResultDTO resultDTO = new ResultDTO();
		userService.register(username, email, password, repeatedPassword, fullname, mobilePhone);

		return resultDTO.toString();
	}

	@RequestMapping("getUserByToken")
	public String getUserByToken(String token) {
		ResultDTO resultDTO = new ResultDTO();
		User user = userService.getUserByToken(token);
		resultDTO.addAttribute("user", user);

		return resultDTO.toString();
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtils.getCookie(request, response, USER_TOKEN_NAME, true);
		ResultDTO resultDTO = new ResultDTO();

		userService.logout(token);

		return resultDTO.toString();
	}

	@RequestMapping("uploadAvatar")
	public String uploadAvatar(MultipartFile file, HttpServletRequest request) {
		ResultDTO resultDTO = new ResultDTO();
		String token = CookieUtils.getCookie(request, USER_TOKEN_NAME);
		User user = null;

		try {
			user = userService.uploadAvatar(token, file.getOriginalFilename(), file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resultDTO.addAttribute("user", user);
		return resultDTO.toString();
	}
}