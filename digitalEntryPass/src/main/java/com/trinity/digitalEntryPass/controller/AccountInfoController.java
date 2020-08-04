package com.trinity.digitalEntryPass.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@RestController
public class AccountInfoController {

	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST)
	public String saveAccountInfo(@RequestBody AccountInfoModel accountInfoModel) {

		List<SelfScreeningModel> g = new ArrayList<>();

		List<SelfScreeningModel> s = new ArrayList<>();
		List<String> c = new ArrayList<>();
		c.add("China");
		c.add("Japan");
		accountInfoModel.setUserType(VisitorType.EMPLOYEE);
		s.add(new SelfScreeningModel(true, true, Calendar.getInstance().getTime(), false, true, true, true, false,c,true));
		accountInfoModel.setSelfScreeningModel(s);
		accountInfoMongoRepository.save(accountInfoModel);

		return "accountInfo is saved";
	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String getAccountInfo() {
		return "accountInfo is saved";
	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.PUT)
	public String updateAccountInfo() {
		return "accountInfo is saved";
	}

}
