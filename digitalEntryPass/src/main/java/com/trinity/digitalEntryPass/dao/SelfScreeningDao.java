package com.trinity.digitalEntryPass.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Component
public class SelfScreeningDao {

	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	public void saveSelfScreeningDao(AccountInfoModel userAccountModel) {
		accountInfoMongoRepository.save(userAccountModel);
	}

	public SelfScreeningModel getLatestSelfScreeningDao(String userName) {
		AccountInfoModel userAccountModel = accountInfoMongoRepository.findById(userName).get();
		return userAccountModel.getSelfScreeningModel().get(userAccountModel.getSelfScreeningModel().size() - 1);
	}
}
