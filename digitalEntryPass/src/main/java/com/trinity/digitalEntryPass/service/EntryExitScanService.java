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

	public int saveCheckIn(FloorDataModel floorData, String sso, String scanType) {

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
			List<EmployeeEntryExitModel> employeeEntryExitListToday = FloorData1
					.get(generalUtils.convertTimeToStringData());
			for (EmployeeEntryExitModel employeeEntryExitModel : employeeEntryExitListToday) {
				if (employeeEntryExitModel.getSso().equals(sso)) {
					if (employeeEntryExitModel.getCheckOutTime() == null) {
						employeeEntryExitModel.setCheckOutTime(Calendar.getInstance().getTime());
					}
				}
			}
			entrExitDao.saveFloorDataForId(floorData);

		}

		return getEmployeesPresentInCommonSpace(floorData.getFloorCode(), generalUtils.convertTimeToStringData());
	}

	public int getEmployeesPresentInCommonSpace(String floorId, String date) {
		List<FloorDataModel> floorData = floorDatarepository.queryForFloorDate(floorId, date);
		int count = 0;
		for (EmployeeEntryExitModel employeeEntryExit : floorData.get(0).getFloorEmployeeData().get(date)) {
			if (employeeEntryExit.getCheckOutTime() == null) {
				count++;
			}
		}
		return count;

	}

	public FloorDataModel getFloorDetails(String floodId) {
		return entrExitDao.getFloorDatafromFloorId(floodId);
	}
}
