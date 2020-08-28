package com.femiliapm.genbe.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femiliapm.genbe.model.dto.DetailBiodataDto;
import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.BiodataRepository;
import com.femiliapm.genbe.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final BiodataRepository biodataRepository;

	@Autowired
	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository) {
		this.personRepository = personRepository;
		this.biodataRepository = biodataRepository;
	}

	@PostMapping
	public DetailBiodataDto insert(@RequestBody DetailBiodataDto dto) {
		PersonEntity personEntity = convertToPersonEntity(dto);
		personRepository.save(personEntity);
		dto.setIdPerson(personEntity.getPersonId());
		BiodataEntity biodataEntity = convertToBiodataEntity(dto);
		biodataRepository.save(biodataEntity);
		return dto;
	}

	@GetMapping
	public List<DetailBiodataDto> get() {
		List<PersonEntity> personEntities = personRepository.findAll();
		List<BiodataEntity> biodataEntities = biodataRepository.findAll();
		List<DetailBiodataDto> detailPerson = personEntities.stream().map(this::convertToDtoFromPerson)
				.collect(Collectors.toList());
		List<DetailBiodataDto> detailBiodata = biodataEntities.stream().map(this::convertToDtoFromBiodata)
				.collect(Collectors.toList());
		List<DetailBiodataDto> detailBiodataDtos = Stream.of(detailPerson, detailBiodata).flatMap(x -> x.stream())
				.collect(Collectors.toList());
		return detailBiodataDtos;
	}

	private PersonEntity convertToPersonEntity(DetailBiodataDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setNiKep(dto.getNik());
		personEntity.setNama(dto.getName());
		personEntity.setAlamat(dto.getAddress());
		return personEntity;
	}

	private BiodataEntity convertToBiodataEntity(DetailBiodataDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity = personRepository.findById(dto.getIdPerson()).get();
		BiodataEntity biodataEntity = new BiodataEntity();
		biodataEntity.setNohp(dto.getHp());
		biodataEntity.setTglLahir(dto.getTgl());
		biodataEntity.setTmptLahir(dto.getTempatLahir());
		biodataEntity.setPersonEntity(personEntity);
		return biodataEntity;
	}

	private DetailBiodataDto convertToDtoFromPerson(PersonEntity personEntity) {
		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
		detailBiodataDto.setNik(personEntity.getNiKep());
		detailBiodataDto.setName(personEntity.getNama());
		detailBiodataDto.setAddress(personEntity.getAlamat());
		return detailBiodataDto;
	}

	private DetailBiodataDto convertToDtoFromBiodata(BiodataEntity biodataEntity) {
		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
		detailBiodataDto.setHp(biodataEntity.getNohp());
		detailBiodataDto.setTgl(biodataEntity.getTglLahir());
		detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
		return detailBiodataDto;
	}
}
