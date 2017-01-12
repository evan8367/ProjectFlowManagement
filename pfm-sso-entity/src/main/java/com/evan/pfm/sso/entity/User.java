package com.evan.pfm.sso.entity;

import java.util.Date;
import java.io.Serializable;


public class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer companyId;
	private Integer positionId;
	private String username;
	private String email;
	private String password;
	private Date createTime;
	private String fullname;
	private String description;
	private String avatar;
	private String mobilePhone;
	private String token;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setCompanyId(Integer companyId){
		this.companyId=companyId;
	}
	public Integer getCompanyId(){
		return companyId;
	}
	public void setPositionId(Integer positionId){
		this.positionId=positionId;
	}
	public Integer getPositionId(){
		return positionId;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setFullname(String fullname){
		this.fullname=fullname;
	}
	public String getFullname(){
		return fullname;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setAvatar(String avatar){
		this.avatar=avatar;
	}
	public String getAvatar(){
		return avatar;
	}
	public void setMobilePhone(String mobilePhone){
		this.mobilePhone=mobilePhone;
	}
	public String getMobilePhone(){
		return mobilePhone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

