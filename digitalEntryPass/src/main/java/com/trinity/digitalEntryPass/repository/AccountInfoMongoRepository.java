package com.trinity.digitalEntryPass.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trinity.digitalEntryPass.model.AccountInfoModel;

@Repository
public interface AccountInfoMongoRepository extends MongoRepository<AccountInfoModel, String> {
	
	AccountInfoModel findBysso(String sso);

}
