package com.trinity.digitalEntryPass.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entryExitCount")
public class EntryExitCountModel {

	@Id
	private String id;

	private Date date;

	private EmployeeEntryExitModel entryExitModel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EmployeeEntryExitModel getEntryExitModel() {
		return entryExitModel;
	}

	public void setEntryExitModel(EmployeeEntryExitModel entryExitModel) {
		this.entryExitModel = entryExitModel;
	}

	@Override
	public String toString() {
		return "EntryExitCountModel [id=" + id + ", date=" + date + ", entryExitModel=" + entryExitModel + "]";
	}

	EntryExitCountModel() {

	}

	public EntryExitCountModel(String id, Date date, EmployeeEntryExitModel entryExitModel) {
		super();
		this.id = id;
		this.date = date;
		this.entryExitModel = entryExitModel;
	}

}
