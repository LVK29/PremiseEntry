package com.trinity.digitalEntryPass.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;
import com.trinity.digitalEntryPass.service.QRCodeService;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class QRCodeController {

	@Autowired
	QRCodeService qrCodeService;
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	/*
	 * Admin gets all qr codes and their respective encrypted values
	 */
	@GetMapping("/allQRCodes")
	public Map<String, String> getAllFloorCode() {
		AccountInfoModel account = accountInfoMongoRepository
				.findBysso(userDetailsServiceImpl.getCurrentUserfromToken());
		if (account.getUserType().equals(VisitorType.ADMIN)) {
			return qrCodeService.generateQRCodeFloorCodeMap();
		}
		return null;
	}

}
