package com.trinity.digitalEntryPass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.service.SelfScreeningService;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class SelfScreeningController {

	
	@Autowired
	SelfScreeningService selfScreeningService;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@RequestMapping(value = "/selfScreening", method = RequestMethod.POST)
	public void saveSelfScreening(@RequestBody SelfScreeningModel selfScreeningModel) {
		selfScreeningService.saveScreeningInfoToCustomer(userDetailsServiceImpl.getCurrentUserfromToken(),
				selfScreeningModel);
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.GET)
	public SelfScreeningModel getSelfScreening() {
		return selfScreeningService.getCustomerScreeningInfo(userDetailsServiceImpl.getCurrentUserfromToken());
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.PUT)
	public void updateSelfScreening(@RequestBody SelfScreeningModel newSelfScreeningModel) {
		selfScreeningService.updateLatestCustomerScreeningInfo(userDetailsServiceImpl.getCurrentUserfromToken(),
				newSelfScreeningModel);
	}

}