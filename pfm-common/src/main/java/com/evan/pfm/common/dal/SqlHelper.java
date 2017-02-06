package com.evan.pfm.common.dal;

import java.util.List;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SqlHelper {
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public SqlHelper(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public <T> T find(String sql, Map<String, Object> param, Class<T> clazz) {
		return (T)this.jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public <T> List<T> findAll(String sql, Map<String, Object> param, Class<T> clazz) {
		return this.jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public void update(String sql, Map<String, Object> param) {
		this.jdbcTemplate.update(sql, param);
	}
	
	public Map<String, Object> queryForMap(String sql, Map<String, ?> param) {
		try {
			return this.jdbcTemplate.queryForMap(sql, param);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
}
