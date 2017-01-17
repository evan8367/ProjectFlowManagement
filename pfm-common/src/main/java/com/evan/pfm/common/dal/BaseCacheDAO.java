package com.evan.pfm.common.dal;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseCacheDAO {
	public final static String TOKEN_KEY_NAME="UserToken";
	public final static String USER_KEY_NAME="User";
	
	protected final static Integer TIMEOUT = 24;
	protected final static TimeUnit TIMEOUT_UNIT = TimeUnit.HOURS;
	
	
	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;
}
