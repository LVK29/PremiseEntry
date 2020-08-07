package com.trinity.digitalEntryPass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trinity.digitalEntryPass.model.FloorDataModel;

@Repository
public interface FloorDataRepository extends MongoRepository<FloorDataModel, String>,CustomDataRepository {

	String floorId = null;

	FloorDataModel findByFloorCode(String name);
	
//	@Query(value="{'_id':'"+floorId+"'}")
//	public Map<String,List<EmployeeEntryExitModel>> getFloorDataForFloorAndDate(String floorId,String Date);
//	//findByAgeFloorData

}
