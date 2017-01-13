package com.evan.pfm.sso.dao;

import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.evan.pfm.common.dal.BaseCacheDAO;
import com.evan.pfm.common.util.UUIDUtils;
import com.evan.pfm.sso.dao.intf.UserCacheDAO;
import com.evan.pfm.sso.entity.User;

@Repository
public class UserCacheDAOImpl extends BaseCacheDAO implements UserCacheDAO {
	
	@Override
	public void deleteUser(Integer id) {
		User user = this.getUser(id);
		if(null != user && !StringUtils.isEmpty(user.getToken())) {
			String tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(id));
			redisTemplate.delete(tokenName);
			tokenName = String.format("%s_%s", TOKEN_KEY_NAME, user.getToken());
			redisTemplate.delete(tokenName);
		}
	}
	
	@Override
	public void deleteUser(String token) {
		if(!StringUtils.isEmpty(token)) {
			String tokenName = String.format("%s_%s", TOKEN_KEY_NAME, token);
			Integer id = this.getUserId(token);
			redisTemplate.delete(tokenName);
			tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(id));
			redisTemplate.delete(tokenName);
			
		}
	}

	@Override
	public String insertUser(User user)  {
		String token = UUIDUtils.getUUID();
		user.setToken(token);
		String tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(user.getId()));
		redisTemplate.opsForValue().set(tokenName, user);
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
		this.insertToken(user.getId(), token);
		
		return token;
	}

	@Override
	public User getUser(String token) {
		String tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(this.getUserId(token)));
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
		return (User)redisTemplate.opsForValue().get(tokenName);
	}
	
	private Integer getUserId(String token) {
		String tokenName = String.format("%s_%s", TOKEN_KEY_NAME, token);
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
		return (Integer)redisTemplate.opsForValue().get(tokenName);
	}
	
	private User getUser(Integer id) {
		String tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(id));
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
		return (User)redisTemplate.opsForHash().get(USER_KEY_NAME, tokenName);
	}
	
	private void insertToken(Integer id, String token) {
		String tokenName = String.format("%s_%s", TOKEN_KEY_NAME, token);
		redisTemplate.opsForValue().set(tokenName, id);
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
	}

	@Override
	public void updateUser(User user) {
		String tokenName = String.format("%s_%s", USER_KEY_NAME, String.valueOf(user.getId()));
		redisTemplate.opsForValue().set(tokenName, user);
		redisTemplate.expire(tokenName, TIMEOUT, TIMEOUT_UNIT);
	}
}
