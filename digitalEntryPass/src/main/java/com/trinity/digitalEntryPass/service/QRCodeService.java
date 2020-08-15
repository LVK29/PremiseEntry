package com.trinity.digitalEntryPass.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.internal.connection.Time;
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;
import com.trinity.digitalEntryPass.repository.FloorDataRepository;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@Service
public class QRCodeService {

	@Autowired
	FloorDataRepository floorDatarepository;
	@Autowired
	GeneralUtils generalUtils;
	// @Autowired
	// AES aes;
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	public Map<String, String> generateQRCodeFloorCodeMap() {

		Map<String, String> qrValuesToFloorMap = new HashMap<>();

		for (String floorCode : GeneralUtils.FLOOR_CODE_VALUES) {
			qrValuesToFloorMap.put(floorCode, AES.encrypt(floorCode, generalUtils.QR_SECRET_KEY));
		}
		addMainQRCodeWithThatDaysSecret(qrValuesToFloorMap);
		return qrValuesToFloorMap;

	}

	private void addMainQRCodeWithThatDaysSecret(Map<String, String> qrValuesToFloorMap) {
		// call sheduling logic to get that day's main_entry_secret
		String mainSecret = generalUtils.QR_SECRET_KEY;
		qrValuesToFloorMap.put("MAIN_ENTRY", AES.encrypt("MAIN_ENTRY", mainSecret));

	}
}
