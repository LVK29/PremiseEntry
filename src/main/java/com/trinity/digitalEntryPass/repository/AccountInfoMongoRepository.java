package com.trinity.digitalEntryPass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.trinity.digitalEntryPass.model.AccountInfoModel;

@Repository
public interface AccountInfoMongoRepository extends MongoRepository<AccountInfoModel, String> {
	
	public AccountInfoModel findBysso(String sso);
	
	@Query(fields = "{'selfScreeningModel.$': 1, 'name':1, 'userType':1 ,'dob':1}")
	public List<AccountInfoModel> findBySelfScreeningModel_Date(String Date);
	
	@Query(value = "{ '_id' : ?0, 'selfScreeningModel.date' : ?1 }",
		   fields = "{'selfScreeningModel.$': 1, 'name':1, 'userType':1,'dob':1}}")
	public AccountInfoModel findByssoAndSelfScreeningModelDate(String sso, String date);
	
	
}
