package com.trinity.digitalEntryPass.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employeeEntryExit")
public class EmployeeEntryExitModel {
	@Id
	private String _id;
	
	private String sso;
	private Date checkInTime;
	private Date checkOutTime;
	
	
	public EmployeeEntryExitModel(String sso, Date checkInTime, Date checkOutTime) {
		super();
		this.sso = sso;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	EmployeeEntryExitModel(){
		
	}

	public String getSso() {
		return sso;
	}

	public void setSso(String sso) {
		this.sso = sso;
	}

	public Date getcheckInTime() {
		return checkInTime;
	}

	public void setcheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	 
	
}
