package com.trinity.digitalEntryPass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.service.GeSelfScreeningForm;
import com.trinity.digitalEntryPass.service.GovtFormGenerator;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
//@RequestMapping("/{userSSO}")
public class SelfScreeningController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	GeSelfScreeningForm geSelfScreeningForm;
	
	@Autowired
	GovtFormGenerator govtFormGenerator;

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

	@RequestMapping(value = "/selfScreeningSubmit", method = RequestMethod.POST)
	public String saveSelfScreening(@RequestParam Boolean scannedEntry) {
		try {
			String user = userDetailsService.getCurrentUserfromToken();
			geSelfScreeningForm.generateForm(user);
			govtFormGenerator.generateForm(user);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "form Genrated";
	}
	
}
