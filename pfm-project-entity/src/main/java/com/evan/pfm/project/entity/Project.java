package com.evan.pfm.project.entity;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable  {
	
	public static final Integer projectStatusNew = 1;
	public static final Integer projectStatusInProgress= 2;
	public static final Integer projectStatusInComplete= 3;
	public static final Integer projectStatusInPending= 4;
	public static final Integer projectStatusInDeleted= 5;
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	private Date planStartDate;
	private Date planEndDate;
	private Date actualStartDate;
	private Date actualEndDate;
	private String cover;
	private Integer status;
	private Date createdTime;
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setPlanStartDate(Date planStartDate){
		this.planStartDate=planStartDate;
	}
	public Date getPlanStartDate(){
		return planStartDate;
	}
	public void setPlanEndDate(Date planEndDate){
		this.planEndDate=planEndDate;
	}
	public Date getPlanEndDate(){
		return planEndDate;
	}
	public void setActualStartDate(Date actualStartDate){
		this.actualStartDate=actualStartDate;
	}
	public Date getActualStartDate(){
		return actualStartDate;
	}
	public void setActualEndDate(Date actualEndDate){
		this.actualEndDate=actualEndDate;
	}
	public Date getActualEndDate(){
		return actualEndDate;
	}
	public void setCover(String cover){
		this.cover=cover;
	}
	public String getCover(){
		return cover;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}


