package com.femiliapm.genbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femiliapm.genbe.model.dto.PendidikanDto;
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

	@Override
	public void insertPendidikan(List<PendidikanDto> pendidikanDtos, Integer idPerson) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pendidikanDtos.size(); i++) {
			PendidikanEntity pendidikanEntity = convertToPendidikanEntity(pendidikanDtos.get(i), idPerson);
			if (pendidikanEntity.equals(null)) {
				
			}
			pendidikanEntity.setPersonEntity(personRepository.findById(idPerson).get());
			pendidikanRepository.save(pendidikanEntity);
		}
	}

	private PendidikanEntity convertToPendidikanEntity(PendidikanDto dto, Integer idPerson) {
		PersonEntity personEntity = new PersonEntity();
		personEntity = personRepository.findById(idPerson).get();
		PendidikanEntity pendidikanEntity = new PendidikanEntity();
		pendidikanEntity.setInstitution(dto.getInstitusi());
		pendidikanEntity.setLevel(dto.getJenjang());
		pendidikanEntity.setThnLulus(dto.getLulus());
		pendidikanEntity.setThnMasuk(dto.getMasuk());
		pendidikanEntity.setPersonEntity(personEntity);
		return pendidikanEntity;
	}

}
