package com.evan.pfm.common.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Autowired
	PropertiesConfig propertiesConfig;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() throws NumberFormatException, IOException {
		JedisPoolConfig redisProperties = new JedisPoolConfig();
		redisProperties.setMinIdle(Integer.valueOf(propertiesConfig.getProperties().getProperty("redis.minIdle")));
		redisProperties.setMaxIdle(Integer.valueOf(propertiesConfig.getProperties().getProperty("redis.maxIdle")));
		redisProperties.setMaxTotal(Integer.valueOf(propertiesConfig.getProperties().getProperty("redis.maxActive")));
		redisProperties.setMaxWaitMillis(Integer.valueOf(propertiesConfig.getProperties().getProperty("redis.maxWait")));
		redisProperties.setTestOnBorrow(true);

		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setHostName(propertiesConfig.getProperties().getProperty("redis.host"));
		jedisConnFactory.setPort(Integer.valueOf(propertiesConfig.getProperties().getProperty("redis.port")));
		jedisConnFactory.setPassword(propertiesConfig.getProperties().getProperty("redis.password"));
		jedisConnFactory.setUsePool(true);
		jedisConnFactory.setPoolConfig(redisProperties);
		jedisConnFactory.afterPropertiesSet();
		return jedisConnFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
	}
}
