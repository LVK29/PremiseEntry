package com.trinity.digitalEntryPass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinity.digitalEntryPass.dao.AccountInfoDao;
import com.trinity.digitalEntryPass.model.AccountInfoModel;

@Service
public class AccountInfoService {

	
	@Autowired
	AccountInfoDao accountInfoDoc;

	public void saveAccountInfo(AccountInfoModel accountInfoModel) {
		
		accountInfoDoc.saveAccountInfo(accountInfoModel);
	}

	public AccountInfoModel getAccountInfo(String userName) {
		return accountInfoDoc.getAccountInfo(userName);
	}

	public void updateAccountInfo(String userName, AccountInfoModel newAccountInfoModel) {
		AccountInfoModel userAccountModel = accountInfoDoc.getAccountInfo(userName);
		userAccountModel.setDob(newAccountInfoModel.getDob());
		userAccountModel.setName(newAccountInfoModel.getName());
		userAccountModel.setUserType(newAccountInfoModel.getUserType());
		accountInfoDoc.saveAccountInfo(userAccountModel);
		
	}

}
