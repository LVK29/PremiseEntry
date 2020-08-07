package com.trinity.digitalEntryPass.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.trinity.digitalEntryPass.model.EmployeeEntryExitModel;

public interface EmployeeEntryExitRepository extends MongoRepository<EmployeeEntryExitModel, String> {

	List<EmployeeEntryExitModel> findByCheckInTimeNull();

	//List<EmployeeEntryExitModel> findByCheckOutTimeNull();

	//@Query("{'checkOutTime' : null}")
	@Query("{ 'fieldToCheck': { $exists: true, $ne: null } }")
	public List<EmployeeEntryExitModel> findByCheckOutTimeNullQuery();

}
