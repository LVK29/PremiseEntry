package com.trinity.digitalEntryPass.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.SelfScreeningModel;

@RestController
@RequestMapping("/{userSSO}")
public class SelfScreeningController {

	@RequestMapping(value = "/selfScreening", method = RequestMethod.POST)
	public String saveSelfScreening(@RequestBody SelfScreeningModel selfScreeningModel) {
		return "";
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.GET)
	public String getSelfScreening() {
		return "";
	}

	@RequestMapping(value = "/selfScreening", method = RequestMethod.PUT)
	public String updateSelfScreening(@RequestBody SelfScreeningModel selfScreeningModel) {
		return "";
	}

}
