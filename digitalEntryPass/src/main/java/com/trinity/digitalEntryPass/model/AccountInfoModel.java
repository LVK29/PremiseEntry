package com.trinity.digitalEntryPass.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Document(collection = "accountInfo")
public class AccountInfoModel {

	public enum VisitorType {
	    EMPLOYEE, CONTRACTOR, ADMIN;
	}
	public enum ScanType {
	    ENTRY, EXIT;
	}
	
	@Id
	@Indexed(unique = true)
	private String sso;
	private String name;
	private String dob;
	private String email;
	private VisitorType userType;
	private Boolean isRegistered;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private List<SelfScreeningModel> selfScreeningModel;
	
	public AccountInfoModel() {

	}

	public AccountInfoModel(String sso, String name, String dob, String email, VisitorType userType,
			Boolean isRegistered, String password, List<SelfScreeningModel> selfScreeningModel) {
		super();
		this.sso = sso;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.userType = userType;
		this.isRegistered = isRegistered;
		this.password = password;
		this.selfScreeningModel = selfScreeningModel;
	}

	@Override
	public String toString() {
		return "AccountInfoModel [sso=" + sso + ", name=" + name + ", dob=" + dob + ", userType=" + userType
				+ ", isRegistered=" + isRegistered + ", password=" + password + ", selfScreeningModel="
				+ selfScreeningModel + "]";
	}

	public String getSso() {
		return sso;
	}

	public void setSso(String sso) {
		this.sso = sso;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public VisitorType getUserType() {
		return userType;
	}

	public void setUserType(VisitorType userType) {
		this.userType = userType;
	}

	public Boolean getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SelfScreeningModel> getSelfScreeningModel() {
		return selfScreeningModel;
	}

	public void setSelfScreeningModel(List<SelfScreeningModel> selfScreeningModel) {
		this.selfScreeningModel = selfScreeningModel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}