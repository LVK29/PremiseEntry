package com.trinity.digitalEntryPass.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "floorData")
public class FloorDataModel {

	private String floorName;
	@Id
	private String floorCode;
	private Map<String,List<EmployeeEntryExitModel>> floorEmployeeData;

	public FloorDataModel() {

	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public FloorDataModel(String floorName, String floorCode,
			Map<String, List<EmployeeEntryExitModel>> floorEmployeeData) {
		super();
		this.floorName = floorName;
		this.floorCode = floorCode;
		this.floorEmployeeData = floorEmployeeData;
	}

	public Map<String, List<EmployeeEntryExitModel>> getFloorEmployeeData() {
		return floorEmployeeData;
	}

	public void setFloorEmployeeData(Map<String, List<EmployeeEntryExitModel>> floorEmployeeData) {
		this.floorEmployeeData = floorEmployeeData;
	}

	 
}