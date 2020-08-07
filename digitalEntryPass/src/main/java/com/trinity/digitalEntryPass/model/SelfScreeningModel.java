package com.trinity.digitalEntryPass.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="selfScreening")
public class SelfScreeningModel {
	
	private Date date;
	private Boolean cough;
	private Boolean fever;
	private Boolean soreThroat;
	private Boolean breathingDifficulty;
	private Boolean isArogaSetuPresent;
	private Boolean isApthamitraPresent;
	private Boolean fromContainmentZone;
	private List<String> visitedCountries;
	private Boolean closeContact;

	public SelfScreeningModel() {

	}

	public SelfScreeningModel(Boolean cough, Boolean fever, Date date, Boolean soreThroat, Boolean breathingDifficulty,
			Boolean isArogaSetuPresent, Boolean isApthamitraPresent, Boolean fromContainmentZone,
			List<String> visitedCountries, Boolean closeContact) {
		super();
		this.cough = cough;
		this.fever = fever;
		this.date = date;
		this.soreThroat = soreThroat;
		this.breathingDifficulty = breathingDifficulty;
		this.isArogaSetuPresent = isArogaSetuPresent;
		this.isApthamitraPresent = isApthamitraPresent;
		this.fromContainmentZone = fromContainmentZone;
		this.visitedCountries = visitedCountries;
		this.closeContact = closeContact;
	}

	@Override
	public String toString() {
		return "SelfScreeningDataModel [cough=" + cough + ", fever=" + fever + ", date=" + date + ", soreThroat="
				+ soreThroat + ", breathingDifficulty=" + breathingDifficulty + ", isArogaSetuPresent="
				+ isArogaSetuPresent + ", isApthamitraPresent=" + isApthamitraPresent + ", fromContainmentZone="
				+ fromContainmentZone + ", visitedCountries=" + visitedCountries + ", closeContact=" + closeContact
				+ "]";
	}

	public Boolean getCough() {
		return cough;
	}

	public void setCough(Boolean cough) {
		this.cough = cough;
	}

	public Boolean getFever() {
		return fever;
	}

	public void setFever(Boolean fever) {
		this.fever = fever;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getSoreThroat() {
		return soreThroat;
	}

	public void setSoreThroat(Boolean soreThroat) {
		this.soreThroat = soreThroat;
	}

	public Boolean getBreathingDifficulty() {
		return breathingDifficulty;
	}

	public void setBreathingDifficulty(Boolean breathingDifficulty) {
		this.breathingDifficulty = breathingDifficulty;
	}

	public Boolean getIsArogaSetuPresent() {
		return isArogaSetuPresent;
	}

	public void setIsArogaSetuPresent(Boolean isArogaSetuPresent) {
		this.isArogaSetuPresent = isArogaSetuPresent;
	}

	public Boolean getIsApthamitraPresent() {
		return isApthamitraPresent;
	}

	public void setIsApthamitraPresent(Boolean isApthamitraPresent) {
		this.isApthamitraPresent = isApthamitraPresent;
	}

	public Boolean getFromContainmentZone() {
		return fromContainmentZone;
	}

	public void setFromContainmentZone(Boolean fromContainmentZone) {
		this.fromContainmentZone = fromContainmentZone;
	}

	public List<String> getVisitedCountries() {
		return visitedCountries;
	}

	public void setVisitedCountries(List<String> visitedCountries) {
		this.visitedCountries = visitedCountries;
	}

	public Boolean getCloseContact() {
		return closeContact;
	}

	public void setCloseContact(Boolean closeContact) {
		this.closeContact = closeContact;
	}

}
