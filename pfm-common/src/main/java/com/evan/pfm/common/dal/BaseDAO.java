package com.evan.pfm.common.dal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO {
	@Autowired
	SqlRegister sqlRegister;
	
	@PostConstruct
	private void RegisterSql() {
		this.sqlRegister.RegisterSqlFile(this.getClass());
	}
//	protected BaseDAO() {
//		SqlRegister.RegisterSqlFile(this.getClass());
//	}

	protected String getSql(String sqlName) {
		return sqlRegister.getSql(sqlName);
	}
}
