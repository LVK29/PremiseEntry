package com.trinity.digitalEntryPass.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employeeEntryExit")
public class EmployeeEntryExitModel {
	@Id
	private String id;
	private String sso;
	private Date inTime;
	private Date outTime;
	
	EmployeeEntryExitModel(){
		
	}

	public EmployeeEntryExitModel(String sso, Date inTime, Date outTime) {
		
		this.sso = sso;
		this.inTime = inTime;
		this.outTime = outTime;
	}
	

	@Override
	public String toString() {
		return "EmployeeEntryExitModel [id=" + id + ", sso=" + sso + ", inTime=" + inTime + ", outTime=" + outTime
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSso() {
		return sso;
	}

	public void setSso(String sso) {
		this.sso = sso;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
	
	
	
	
}
