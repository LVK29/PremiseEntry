package com.trinity.digitalEntryPass.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Component
public class AccountInfoDao{

	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	public void saveAccountInfo(AccountInfoModel userAccountModel) {
		accountInfoMongoRepository.save(userAccountModel);
	}

	public AccountInfoModel getAccountInfo(String userName) {
		return accountInfoMongoRepository.findById(userName).get();
	}

	public void updateAccountInfo(AccountInfoModel userAccountModel) {
		accountInfoMongoRepository.save(userAccountModel);
	}
}
