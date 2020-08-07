package com.trinity.digitalEntryPass.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;
import com.trinity.digitalEntryPass.model.FloorDataModel;
import com.trinity.digitalEntryPass.repository.FloorDataRepository;

@Component
public class EntryExitScanDao {

	@Autowired
	FloorDataRepository floorDataRepository;

	public FloorDataModel getFloorDatafromFloorId(String floorId) {
		FloorDataModel floorData = floorDataRepository.findByFloorCode(floorId);

		return floorData;
	}

	public void saveFloorDataForId(FloorDataModel floorData) {

		floorDataRepository.save(floorData);
	}

	public void getFloorDataForFloorAndDate(FloorDataModel floorData) {

		floorDataRepository.save(floorData);
	}

}
