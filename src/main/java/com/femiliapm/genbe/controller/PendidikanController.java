package com.femiliapm.genbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femiliapm.genbe.model.dto.PendidikanDto;
import com.femiliapm.genbe.model.dto.StatusMessageDto;
import com.femiliapm.genbe.model.entity.PendidikanEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.PendidikanRepository;
import com.femiliapm.genbe.repository.PersonRepository;
import com.femiliapm.genbe.service.AllService;
import com.femiliapm.genbe.service.AllServiceImpl;

@RestController
@RequestMapping("/pendidikan")
public class PendidikanController {
//	private final PendidikanRepository pendidikanRepository;
	private final PersonRepository personRepository;

	@Autowired
	private AllService pendidikanService = new AllServiceImpl();

	@Autowired
	public PendidikanController(PendidikanRepository pendidikanRepository, PersonRepository personRepository) {
//		this.pendidikanRepository = pendidikanRepository;
		this.personRepository = personRepository;
	}

	@PostMapping
	public StatusMessageDto insert(@RequestBody PendidikanDto dto) {
		if (dto.equals(null)) {
			return dataGagal();
		} else {
			PendidikanEntity pendidikanEntity = convertToPendidikanEntity(dto);
//			pendidikanRepository.save(pendidikanEntity);
			pendidikanService.insertDataPendidikan(pendidikanEntity);
			return dataBerhasil();
		}
	}

	private StatusMessageDto dataBerhasil() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("true");
		statusMessageDto.setMessage("data berhasil masuk");
		return statusMessageDto;
	}

	private StatusMessageDto dataGagal() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("false");
		statusMessageDto.setMessage("data gagal masuk");
		return statusMessageDto;
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

//	private PendidikanDto convertToPendidikanDto(PendidikanEntity entity) {
//		PendidikanDto pendidikanDto = new PendidikanDto();
//		pendidikanDto.setIdPerson(entity.getPersonEntity().getPersonId());
//		pendidikanDto.setInstitusi(entity.getInstitution());
//		pendidikanDto.setJenjang(entity.getLevel());
//		pendidikanDto.setLulus(entity.getThnLulus());
//		pendidikanDto.setMasuk(entity.getThnMasuk());
//		return pendidikanDto;
//	}
}
