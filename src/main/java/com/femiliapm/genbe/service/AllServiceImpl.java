package com.femiliapm.genbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PendidikanEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.BiodataRepository;
import com.femiliapm.genbe.repository.PendidikanRepository;
import com.femiliapm.genbe.repository.PersonRepository;

@Service
@Transactional
public class AllServiceImpl implements AllService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BiodataRepository biodataRepository;
	@Autowired
	private PendidikanRepository pendidikanRepository;

	@Override
	public PersonEntity insertDataPerson(PersonEntity personEntity) {
		// TODO Auto-generated method stub
		PersonEntity entity = personRepository.save(personEntity);
		return entity;
	}

	@Override
	public BiodataEntity insertDataBiodata(BiodataEntity biodataEntity) {
		// TODO Auto-generated method stub
		BiodataEntity entity = biodataRepository.save(biodataEntity);
		return entity;
	}

	@Override
	public PendidikanEntity insertDataPendidikan(PendidikanEntity pendidikanEntity) {
		// TODO Auto-generated method stub
		PendidikanEntity entity = pendidikanRepository.save(pendidikanEntity);
		return entity;
	}
}