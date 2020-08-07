package com.trinity.digitalEntryPass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.service.SelfScreeningService;

@RestController
public class SelfScreeningController {

	@Autowired
	SelfScreeningService selfScreeningService;

	@RequestMapping(value = "/selfScreening", method = RequestMethod.POST)
	public void saveSelfScreening(@RequestBody SelfScreeningModel selfScreeningModel) {
		selfScreeningService.saveScreeningInfoToCustomer("212668393", selfScreeningModel);
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.GET)
	public SelfScreeningModel getSelfScreening() {
		return selfScreeningService.getCustomerScreeningInfo("212668393");
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.PUT)
	public void updateSelfScreening(@RequestBody SelfScreeningModel newSelfScreeningModel) {
		selfScreeningService.updateLatestCustomerScreeningInfo("212668393", newSelfScreeningModel);
	}

}