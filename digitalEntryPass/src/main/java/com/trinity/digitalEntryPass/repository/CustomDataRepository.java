package com.trinity.digitalEntryPass.repository;

import java.util.List;

import com.trinity.digitalEntryPass.model.FloorDataModel;

public interface CustomDataRepository {

	public List<FloorDataModel> queryForFloorDate(String floorID, String Date);
}
