package com.evan.pfm.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.evan.pfm.*" })
@Import({DataAccessConfig.class, PropertiesConfig.class, RedisConfig.class})
public class AppConfig {

}