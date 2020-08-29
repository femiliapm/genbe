package com.femiliapm.genbe.service;

import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PendidikanEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;

public interface AllService {
	PersonEntity insertDataPerson(PersonEntity personEntity);
	BiodataEntity insertDataBiodata(BiodataEntity biodataEntity);
	PendidikanEntity insertDataPendidikan(PendidikanEntity pendidikanEntity);
}
