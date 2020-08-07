package com.trinity.digitalEntryPass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.digitalEntryPass.model.EmployeeEntryExitModel;
import com.trinity.digitalEntryPass.model.FloorDataModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.ScanType;
import com.trinity.digitalEntryPass.service.Demo;
import com.trinity.digitalEntryPass.service.EntryExitScanService;
import com.trinity.digitalEntryPass.service.GeneralUtils;
import com.trinity.digitalEntryPass.service.impl.UserDetailsServiceImpl;

@RestController
public class EntryExitScanController {

	@Autowired
	Demo demo;

	@Autowired
	EntryExitScanService entryExitScanService;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	GeneralUtils generalUtils;

	// code can be entry, floorentry, exit,

	@RequestMapping(path = "/entryExitScan/{floorId}", method = RequestMethod.POST)
	public int scanEntryController(@PathVariable String floorId) {

		// get qr code
		// String floorId = "7F_Entry";
		// String floorId = "7F_Exit";
		String sso = userDetailsServiceImpl.getCurrentUserfromToken();
		String[] floorIdStrings = floorId.split("_", 2);
		String floorIdCode = floorIdStrings[0];
		String scanType = floorIdStrings[1].toUpperCase();

		FloorDataModel floorData = entryExitScanService.getFloorDetails(floorIdCode);

		if (floorData == null) {
			Map<String, List<EmployeeEntryExitModel>> newFloorData = new HashMap<String, List<EmployeeEntryExitModel>>();
			floorData = new FloorDataModel(floorIdCode, floorIdCode, newFloorData);

		}
		
		// mark all possible open and exit entryes
		if(entryExitScanService.markAllPossibleScan(scanType, floorIdCode, sso)){
			System.out.println("Changed a few incorrect time stamps for entry/exit");
		}
		// get updated floorData
		FloorDataModel updatedFloorData = entryExitScanService.getFloorDetails(floorIdCode);

		// save scan entry
		entryExitScanService.saveQRFloorScan(updatedFloorData, sso, scanType);
				
		// return entry count 
		return entryExitScanService.getEmployeesPresentInCommonSpace(floorData.getFloorCode(),
				generalUtils.convertTimeToStringData());


		// demo.encryptTest();
	}

}
