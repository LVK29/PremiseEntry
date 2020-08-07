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
import com.trinity.digitalEntryPass.service.Demo;
import com.trinity.digitalEntryPass.service.EntryExitScanService;

@RestController
public class EntryExitScanController {

	@Autowired
	Demo demo;

	@Autowired
	EntryExitScanService entryExitScanService;
	// post scans code

	// code can be entry, floorentry, exit,

	@RequestMapping(path = "/entryExitScan/{floorId}", method = RequestMethod.POST)
	public int scanEntryController(@PathVariable String floorId) {

		// get qr code
		// String floorId = "7F_Entry";
		// String floorId = "7F_Exit";
		String sso = "212668393";
		String[] floorIdStrings = floorId.split("_", 2);
		String floorIdCode = floorIdStrings[0];
		String scanType = floorIdStrings[1].toUpperCase();

		FloorDataModel floorData = entryExitScanService.getFloorDetails(floorIdCode);

		if (floorData == null) {
			Map<String, List<EmployeeEntryExitModel>> newFloorData = new HashMap<String, List<EmployeeEntryExitModel>>();
			floorData = new FloorDataModel(floorIdCode, floorIdCode, newFloorData);

		}
		return entryExitScanService.saveCheckIn(floorData, sso, scanType);
		// FloorDataModel floorDetails = entryExitScanService.getFloorDetails();
		// floorDetails.getFloorData()
		// save the time to the respective floor code with sso

		// if its entry code check for null exit codes and save time with
		// warning

		// if its exit code check for entry codes and save time with warning

		// get count of all ppl in the floor currently

		// demo.encryptTest();
	}

}
