package com.evan.pfm.common.dal;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO {

	SqlRegister sqlRegister;
	
	@Autowired
	protected SqlHelper sqlHelper;
	
	public BaseDAO() {
		this.sqlRegister = new SqlRegister();
		this.sqlRegister.RegisterSqlFile(this.getClass());
	}
	
//	@PostConstruct
//	private void RegisterSql() {
//		this.sqlRegister.RegisterSqlFile(this.getClass());
//	}

	protected String getSql(String sqlName) {
		return sqlRegister.getSql(sqlName);
	}
}
