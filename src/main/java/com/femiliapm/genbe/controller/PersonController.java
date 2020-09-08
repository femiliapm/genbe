package com.femiliapm.genbe.controller;

import java.time.LocalDate;
import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.List;

//import java.util.stream.Collectors;
//import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femiliapm.genbe.model.dto.DetailBiodataDto;
import com.femiliapm.genbe.model.dto.StatusMessageDto;
import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.BiodataRepository;
import com.femiliapm.genbe.repository.PersonRepository;
import com.femiliapm.genbe.service.AllService;
import com.femiliapm.genbe.service.AllServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final BiodataRepository biodataRepository;

	@Autowired
	private AllService personService = new AllServiceImpl();

	@Autowired
	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository) {
		this.personRepository = personRepository;
		this.biodataRepository = biodataRepository;
	}

	@PostMapping
	public StatusMessageDto insert(@RequestBody DetailBiodataDto dto) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(dto.getTgl());
//		int month = cal.get(Calendar.MONTH) + 1;
//		int year = cal.get(Calendar.YEAR);
//		int day = cal.get(Calendar.DATE);

		LocalDate localDate = dto.getTgl().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int tahun = localDate.getYear();
		int bulan = localDate.getMonthValue();
		int hari = localDate.getDayOfMonth();

		if (dto.getNik().length() != 16) {
			return dataGagalNik();
		} else if (2020 - tahun < 30 && bulan < 9 && hari < 7) {
			return dataGagalUmur();
		} else {
			PersonEntity personEntity = convertToPersonEntity(dto);
//			personRepository.save(personEntity);
			personService.insertDataPerson(personEntity);
			dto.setIdPerson(personEntity.getPersonId());
			BiodataEntity biodataEntity = convertToBiodataEntity(dto);
//			biodataRepository.save(biodataEntity);
			personService.insertDataBiodata(biodataEntity);
			return dataBerhasil();
		}
	}

	@GetMapping("/{nik}")
	public DetailBiodataDto get(@PathVariable Integer idPerson) {
		PersonEntity personEntity = personRepository.findById(idPerson).get();
		BiodataEntity biodataEntity = biodataRepository.findById(idPerson).get();
		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
		detailBiodataDto.setNik(personEntity.getNiKep());
		detailBiodataDto.setName(personEntity.getNama());
		detailBiodataDto.setAddress(personEntity.getAlamat());
		detailBiodataDto.setHp(biodataEntity.getNohp());
		detailBiodataDto.setTgl(biodataEntity.getTglLahir());
		detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
		return detailBiodataDto;
	}

	private StatusMessageDto dataBerhasil() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("true");
		statusMessageDto.setMessage("data berhasil masuk");
		return statusMessageDto;
	}

	private StatusMessageDto dataGagalNik() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("false");
		statusMessageDto.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
		return statusMessageDto;
	}

	private StatusMessageDto dataGagalUmur() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("false");
		statusMessageDto.setMessage("data gagal masuk, umur kurang dari 30 tahun");
		return statusMessageDto;
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

//	private DetailBiodataDto convertToDtoFromPerson(PersonEntity personEntity) {
//		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
//		detailBiodataDto.setNik(personEntity.getNiKep());
//		detailBiodataDto.setName(personEntity.getNama());
//		detailBiodataDto.setAddress(personEntity.getAlamat());
//		return detailBiodataDto;
//	}
//
//	private DetailBiodataDto convertToDtoFromBiodata(BiodataEntity biodataEntity) {
//		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
//		detailBiodataDto.setHp(biodataEntity.getNohp());
//		detailBiodataDto.setTgl(biodataEntity.getTglLahir());
//		detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
//		return detailBiodataDto;
//	}
}
