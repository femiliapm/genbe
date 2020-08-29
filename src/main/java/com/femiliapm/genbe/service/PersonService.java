package com.femiliapm.genbe.service;

import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;

public interface PersonService {
	PersonEntity insertDataPerson(PersonEntity personEntity);
	BiodataEntity insertDataBiodata(BiodataEntity biodataEntity);
}
