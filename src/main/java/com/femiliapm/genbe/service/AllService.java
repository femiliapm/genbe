package com.femiliapm.genbe.service;

import java.util.List;

import com.femiliapm.genbe.model.dto.PendidikanDto;
import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PendidikanEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;

public interface AllService {
	PersonEntity insertDataPerson(PersonEntity personEntity);

	BiodataEntity insertDataBiodata(BiodataEntity biodataEntity);

	PendidikanEntity insertDataPendidikan(PendidikanEntity pendidikanEntity);

	public void insertPendidikan(List<PendidikanDto> pendidikanDtos, Integer idPerson);
}
