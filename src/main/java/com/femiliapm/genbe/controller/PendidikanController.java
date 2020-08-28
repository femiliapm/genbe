package com.femiliapm.genbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femiliapm.genbe.model.dto.PendidikanDto;
import com.femiliapm.genbe.model.entity.PendidikanEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.PendidikanRepository;
import com.femiliapm.genbe.repository.PersonRepository;

@RestController
@RequestMapping("/pendidikan")
public class PendidikanController {
	private final PendidikanRepository pendidikanRepository;
	private final PersonRepository personRepository;

	@Autowired
	public PendidikanController(PendidikanRepository pendidikanRepository, PersonRepository personRepository) {
		this.pendidikanRepository = pendidikanRepository;
		this.personRepository = personRepository;
	}

	@PostMapping
	public PendidikanDto insert(@RequestBody PendidikanDto dto) {
		PendidikanEntity pendidikanEntity = convertToPendidikanEntity(dto);
		pendidikanRepository.save(pendidikanEntity);
		return convertToPendidikanDto(pendidikanEntity);
	}

	private PendidikanEntity convertToPendidikanEntity(PendidikanDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity = personRepository.findById(dto.getIdPerson()).get();
		PendidikanEntity pendidikanEntity = new PendidikanEntity();
		pendidikanEntity.setInstitution(dto.getInstitusi());
		pendidikanEntity.setLevel(dto.getJenjang());
		pendidikanEntity.setThnLulus(dto.getLulus());
		pendidikanEntity.setThnMasuk(dto.getMasuk());
		pendidikanEntity.setPersonEntity(personEntity);
		return pendidikanEntity;
	}

	private PendidikanDto convertToPendidikanDto(PendidikanEntity entity) {
		PendidikanDto pendidikanDto = new PendidikanDto();
		pendidikanDto.setIdPerson(entity.getPersonEntity().getPersonId());
		pendidikanDto.setInstitusi(entity.getInstitution());
		pendidikanDto.setJenjang(entity.getLevel());
		pendidikanDto.setLulus(entity.getThnLulus());
		pendidikanDto.setMasuk(entity.getThnMasuk());
		return pendidikanDto;
	}
}
