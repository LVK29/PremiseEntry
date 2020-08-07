package com.trinity.digitalEntryPass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.service.SelfScreeningService;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class AdminReportController {

	@Autowired
	SelfScreeningService selfScreeningService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@RequestMapping("/allSelfSceeningData")
	public List<SelfScreeningModel> getAllSelfScreeningForDate(){
		
	//	selfScreeningService.getAllCustomerScreeningDataForDate(null);
	
		return null;
	}
}
