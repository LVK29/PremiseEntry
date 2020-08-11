package com.trinity.digitalEntryPass.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinity.digitalEntryPass.dao.EntryExitScanDao;
import com.trinity.digitalEntryPass.model.AccountInfoModel.ScanType;
import com.trinity.digitalEntryPass.model.EmployeeEntryExitModel;
import com.trinity.digitalEntryPass.model.FloorDataModel;
import com.trinity.digitalEntryPass.repository.EmployeeEntryExitRepository;
import com.trinity.digitalEntryPass.repository.FloorDataRepository;

@Service
public class EntryExitScanService {

	@Autowired
	GeneralUtils generalUtils;

	@Autowired
	EntryExitScanDao entrExitDao;

	@Autowired
	EntryExitScanService entryExitScanService;

	@Autowired
	EmployeeEntryExitRepository employeeEntryExitRepository;

	@Autowired
	FloorDataRepository floorDatarepository;

	public void saveQRFloorScan(FloorDataModel floorData) {
		floorDatarepository.save(floorData);

	}

	public void saveQRFloorScan(FloorDataModel floorData, String sso, String scanType) {

		if (scanType.equals(ScanType.ENTRY.toString())) {
			EmployeeEntryExitModel employeeEntryExit = new EmployeeEntryExitModel(sso, Calendar.getInstance().getTime(),
					null);
			// append entry to the existing floor-date entry
			if (floorData.getFloorEmployeeData().keySet().contains(generalUtils.convertTimeToStringData())) {
				List<EmployeeEntryExitModel> entryExitCountList = floorData.getFloorEmployeeData()
						.get(generalUtils.convertTimeToStringData());
				entryExitCountList.add(employeeEntryExit);
			} else {
				// first scan to the floor
				List<EmployeeEntryExitModel> entryExitCountList = new ArrayList<>();
				entryExitCountList.add(employeeEntryExit);
				floorData.getFloorEmployeeData().put(generalUtils.convertTimeToStringData(), entryExitCountList);

			}
			// save entry/exit data
			entrExitDao.saveFloorDataForId(floorData);
		}

		else if (scanType.equals(ScanType.EXIT.toString())) {
			Map<String, List<EmployeeEntryExitModel>> FloorData1 = floorData.getFloorEmployeeData();
			if (floorData.getFloorEmployeeData().keySet().contains(generalUtils.convertTimeToStringData())) {
				List<EmployeeEntryExitModel> employeeEntryExitListToday = FloorData1
						.get(generalUtils.convertTimeToStringData());
				for (EmployeeEntryExitModel employeeEntryExitModel : employeeEntryExitListToday) {
					if (employeeEntryExitModel.getSso().equals(sso)) {
						if (employeeEntryExitModel.getCheckOutTime() == null) {
							employeeEntryExitModel.setCheckOutTime(Calendar.getInstance().getTime());
						}
					}
				}
			}
			entrExitDao.saveFloorDataForId(floorData);

		}

	}

	public Boolean markAllPossibleScan(String scanType, String currentFloorCode, String sso) {
		// String[] floorCodes = { "GFL", "GFR", "1F", "2F", "3F", "4F", "5F",
		// "6F", "7F" };
		String today = generalUtils.convertTimeToStringData();

		// get all floor data
		Boolean proxyRecordAdded = false;
		List<FloorDataModel> floorDatas = floorDatarepository.queryAllFloorDate(today);
		if (floorDatas.size() > 0) {
			for (FloorDataModel floorData : floorDatas) {
				// if (!floorData.getFloorCode().equals(currentFloorCode)) {
				List<EmployeeEntryExitModel> allFloorEmployeeDataForToday = floorData.getFloorEmployeeData().get(today);
				if (allFloorEmployeeDataForToday != null) {
					for (EmployeeEntryExitModel floorEmpData : allFloorEmployeeDataForToday) {

						if ((scanType.equals(ScanType.ENTRY.toString()))) {
							if (floorEmpData.getSso().equals(sso)) {
								if (floorEmpData.getCheckOutTime() == null) {
									floorEmpData.setCheckOutTime(Calendar.getInstance().getTime());
									proxyRecordAdded = true;
								}
								if (floorEmpData.getcheckInTime() == null) {
									floorEmpData.setcheckInTime(Calendar.getInstance().getTime());
									proxyRecordAdded = true;
								}

							}
						}

					}
				}

				floorDatarepository.save(floorData);
			}

		}

		return proxyRecordAdded;

	}

	public int getEmployeesPresentInCommonSpace(String floorId, String date) {
		List<FloorDataModel> floorData = floorDatarepository.queryForFloorDate(floorId, date);
		int count = 0;
		if(floorData.get(0).getFloorEmployeeData().containsKey(date)){
			for (EmployeeEntryExitModel employeeEntryExit : floorData.get(0).getFloorEmployeeData().get(date)) {
				if (employeeEntryExit.getCheckOutTime() == null) {
					count++;
				}
			}
		}
		
		return count;

	}

	public FloorDataModel getFloorDetails(String floodId) {
		return entrExitDao.getFloorDatafromFloorId(floodId);
	}
}
