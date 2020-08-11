package com.trinity.digitalEntryPass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinity.digitalEntryPass.dao.AccountInfoDao;
import com.trinity.digitalEntryPass.dao.SelfScreeningDao;
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;
import com.trinity.digitalEntryPass.repository.EmployeeEntryExitRepository;
import com.trinity.digitalEntryPass.repository.FloorDataRepository;

@Service
public class SelfScreeningService {

	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	@Autowired
	SelfScreeningDao selfScreeningDao;

	@Autowired
	GeneralUtils generalUtils;

	@Autowired
	AccountInfoDao accountInfoDoc;

	@Autowired
	FloorDataRepository floorDatarepository;

	@Autowired
	EmployeeEntryExitRepository employeeExitRepository;

	public String saveScreeningInfoToCustomer(String userName, SelfScreeningModel newSelfScreeningData) {
		AccountInfoModel userAccountModel = accountInfoDoc.getAccountInfo(userName);
		List<SelfScreeningModel> selfScreeningList = userAccountModel.getSelfScreeningModel();
		if (selfScreeningList == null) {
			selfScreeningList = new ArrayList<SelfScreeningModel>();
		}
		newSelfScreeningData.setDate(generalUtils.convertTimeToStringData());
		selfScreeningList.add(newSelfScreeningData);
		userAccountModel.setSelfScreeningModel(selfScreeningList);
		accountInfoDoc.saveAccountInfo(userAccountModel);
		return "Screening information saved";
	}

	public SelfScreeningModel getCustomerScreeningInfo(String userName) {
		AccountInfoModel userAccountModel = accountInfoMongoRepository.findById(userName).get();
		if (userAccountModel.getSelfScreeningModel() != null) {
			return userAccountModel.getSelfScreeningModel().get(userAccountModel.getSelfScreeningModel().size() - 1);
		}
		return null;
	}

	public String updateLatestCustomerScreeningInfo(String userName, SelfScreeningModel newSelfScreeningModel) {
		AccountInfoModel userAccountModel = accountInfoMongoRepository.findById(userName).get();
		if (userAccountModel.getSelfScreeningModel() != null) {
			SelfScreeningModel selfScreeningList = userAccountModel.getSelfScreeningModel()
					.get(userAccountModel.getSelfScreeningModel().size() - 1);

			selfScreeningList.setBreathingDifficulty(newSelfScreeningModel.getBreathingDifficulty());
			selfScreeningList.setCloseContact(newSelfScreeningModel.getCloseContact());
			selfScreeningList.setCough(newSelfScreeningModel.getCough());
			selfScreeningList.setDate(generalUtils.convertTimeToStringData());
			selfScreeningList.setFever(newSelfScreeningModel.getFever());
			selfScreeningList.setFromContainmentZone(newSelfScreeningModel.getFromContainmentZone());
			selfScreeningList.setIsApthamitraPresent(newSelfScreeningModel.getIsApthamitraPresent());
			selfScreeningList.setIsArogaSetuPresent(newSelfScreeningModel.getIsArogaSetuPresent());
			selfScreeningList.setSoreThroat(newSelfScreeningModel.getSoreThroat());
			selfScreeningList.setVisitedCountries(newSelfScreeningModel.getVisitedCountries());
			selfScreeningDao.saveSelfScreeningDao(userAccountModel);
			return "Update successful";
		}
		return "No Self Screening information present";
	}

	// public int getEmployeesPresentInCommonSpace(String date) {
	// List<FloorDataModel> floorData = floorDatarepository.queryForFloorDate();
	// int count = 0;
	// for (EmployeeEntryExitModel employeeEntryExit :
	// floorData.get(0).getFloorEmployeeData().get(date)) {
	// if (employeeEntryExit.getCheckOutTime() == null) {
	// count++;
	// }
	// }
	// return count;
	//
	// }

}
