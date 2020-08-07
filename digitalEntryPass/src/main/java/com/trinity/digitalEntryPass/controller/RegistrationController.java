package com.trinity.digitalEntryPass.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class RegistrationController {
	
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveAccountInfo(@RequestBody AccountInfoModel accountInfoModel) {

		List<SelfScreeningModel> g = new ArrayList<>();

		List<SelfScreeningModel> s = new ArrayList<>();
		List<String> c = new ArrayList<>();
		c.add("China");
		c.add("Japan");
		accountInfoModel.setUserType(VisitorType.EMPLOYEE);
		System.out.println(passwordEncoder.encode(accountInfoModel.getPassword()));
		accountInfoModel.setPassword(passwordEncoder.encode(accountInfoModel.getPassword()));
		s.add(new SelfScreeningModel(true, true, Calendar.getInstance().getTime(), false, true, true, true, false,c,true));
		accountInfoModel.setSelfScreeningModel(s);
		accountInfoMongoRepository.save(accountInfoModel);

		return "accountInfo is saved";
	}

}
