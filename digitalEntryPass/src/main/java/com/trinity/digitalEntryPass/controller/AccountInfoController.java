package com.trinity.digitalEntryPass.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;
import com.trinity.digitalEntryPass.service.AccountInfoService;
import com.trinity.digitalEntryPass.service.GeSelfScreeningForm;
import com.trinity.digitalEntryPass.service.GovtFormGenerator;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class AccountInfoController {

	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	@Autowired
	AccountInfoService accountInfoService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	GovtFormGenerator govtFormGenerator;
	
	@Autowired
	GeSelfScreeningForm geSelfScreeningForm;
	
	private String GOVTFORM = "govtForm";
	private String VISITORFORM = "visForm";
	


	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST)
	public void saveAccountInfo(@RequestBody AccountInfoModel accountInfoModel) {

		// remove these later on
		List<SelfScreeningModel> s = new ArrayList<>();
		List<String> c = new ArrayList<>();
		c.add("China");
		c.add("Japan");
		accountInfoModel.setUserType(VisitorType.EMPLOYEE);
		s.add(new SelfScreeningModel(true, true, "07/08/2020", false, true, true, true, false, c,
				true));
		accountInfoModel.setSelfScreeningModel(s);

		accountInfoMongoRepository.save(accountInfoModel);

	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public AccountInfoModel getAccountInfo() {
		 accountInfoMongoRepository.findBySelfScreeningModel_Date("08/08/2020");
		 return accountInfoService.getAccountInfo(userDetailsServiceImpl.getCurrentUserfromToken());
	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.PUT)
	public void updateAccountInfo(@RequestBody AccountInfoModel newAccountInfoModel) {
		accountInfoService.updateAccountInfo(userDetailsServiceImpl.getCurrentUserfromToken(), newAccountInfoModel);
	}

	@RequestMapping(value = "/accountInfo/{screenDate}", method = RequestMethod.GET)
	public List<AccountInfoModel> getAccountInfoByScreenDate(@PathVariable String screenDate) {
		screenDate=screenDate.replace("-", "/");
		AccountInfoModel account =accountInfoMongoRepository.findBysso(userDetailsServiceImpl.getCurrentUserfromToken());
		if(account.getUserType().equals(VisitorType.ADMIN))
			return accountInfoMongoRepository.findBySelfScreeningModel_Date(screenDate);
		return null;
	}
	
	@RequestMapping(value = "/accountInfo/documentFile", method = RequestMethod.POST, produces=MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getDocuments(@RequestBody Map<String, String> request) throws IOException
	{{
		byte[] formData = null;
		String sso = request.get("sso");
		String date=request.get("date");
		String docType=request.get("docType");
		date=date.replace("-", "/");
		try {
			AccountInfoModel account =accountInfoMongoRepository.findBysso(userDetailsServiceImpl.getCurrentUserfromToken());
			if(docType!=null && docType.equals(GOVTFORM))
			{
					formData = govtFormGenerator.generateForm(date,sso);

			}
			if(docType!=null && docType.equals(VISITORFORM) && account.getUserType().equals(VisitorType.CONTRACTOR))
			{
				formData=geSelfScreeningForm.generateForm(date, sso);
			}
		}
		
		 catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formData; 
	}
	}
}
