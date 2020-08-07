package com.trinity.digitalEntryPass.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.trinity.digitalEntryPass.model.FloorDataModel;
import com.trinity.digitalEntryPass.repository.CustomDataRepository;

public class CustomDataRepositoryImpl implements CustomDataRepository{

	private final MongoTemplate mongoTemplate;

	@Autowired
	public CustomDataRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<FloorDataModel> queryForFloorDate(String floorID,String Date) {

		Query query = new Query(Criteria.where("_id").is(floorID));
		query.fields().include("floorEmployeeData."+Date);
		return mongoTemplate.find(query, FloorDataModel.class);
	}

}
