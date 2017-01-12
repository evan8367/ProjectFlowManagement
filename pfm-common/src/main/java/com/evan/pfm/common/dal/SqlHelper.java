package com.evan.pfm.common.dal;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

public class SqlHelper {
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public SqlHelper(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public <T> T find(String sql, Map<String, Object> paramMap, Class<T> clazz) {
		return (T)this.jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public <T> List<T> findAll(String sql, Map<String, Object> paramMap, Class<T> clazz) {
		return (List<T>)this.jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public void update(String sql, Map<String, Object> paramMap) {
		this.jdbcTemplate.update(sql, paramMap);
	}
	
	public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) {
		try {
			return this.jdbcTemplate.queryForMap(sql, paramMap);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	public <T> List<T> queryForList(String sql, Map<String, ?> map, Class<T> clazz) {
		return this.jdbcTemplate.query(sql, map, new BeanPropertyRowMapper<T>(clazz));
	}
	

}
