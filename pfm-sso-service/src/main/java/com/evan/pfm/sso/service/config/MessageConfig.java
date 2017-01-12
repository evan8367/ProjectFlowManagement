package com.evan.pfm.sso.service.config;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import com.evan.pfm.sso.service.UserServiceImpl;

@Component
public class MessageConfig {
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Bean
	public MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter(new Receiver(), "receiveMessage");
	}

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		ArrayList<Topic> topicList = new ArrayList<Topic>();
		topicList.add(new PatternTopic("user"));
		topicList.add(new PatternTopic("role"));
		container.addMessageListener(listenerAdapter, topicList);
		return container;
	}
	
	public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

	public class Receiver {
		public void receiveMessage(String message, String channel) {
			switch (channel) {
			case "user":
				userService.receiveMessage(message);
				break;
			}
		}
	}
}
