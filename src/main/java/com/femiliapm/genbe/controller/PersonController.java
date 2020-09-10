package com.femiliapm.genbe.controller;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.femiliapm.genbe.model.dto.StatusMessageDto2;
import com.femiliapm.genbe.model.dto.StatusMessageDto;
import com.femiliapm.genbe.model.entity.BiodataEntity;
import com.femiliapm.genbe.model.entity.PersonEntity;
import com.femiliapm.genbe.repository.BiodataRepository;
import com.femiliapm.genbe.repository.PendidikanRepository;
import com.femiliapm.genbe.repository.PersonRepository;
import com.femiliapm.genbe.service.AllService;
import com.femiliapm.genbe.service.AllServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final BiodataRepository biodataRepository;
	private final PendidikanRepository pendidikanRepository;

	@Autowired
	private AllService personService = new AllServiceImpl();

	@Autowired
	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository,
			PendidikanRepository pendidikanRepository) {
		this.personRepository = personRepository;
		this.biodataRepository = biodataRepository;
		this.pendidikanRepository = pendidikanRepository;
	}

	@PostMapping
	public StatusMessageDto insert(@RequestBody DetailBiodataDto dto) {
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
			personEntity = personRepository.findById(personEntity.getPersonId()).get();
			BiodataEntity biodataEntity = convertToBiodataEntity(dto);
//			biodataRepository.save(biodataEntity);
			personService.insertDataBiodata(biodataEntity);
			return dataBerhasil();
		}
	}

	@GetMapping
	public List<DetailBiodataDto> get() {
		List<PersonEntity> personEntities = personRepository.findAll();
		List<DetailBiodataDto> detailBiodataDtos = new ArrayList<>();
		for (PersonEntity p : personEntities) {
			DetailBiodataDto dto = new DetailBiodataDto();
			BiodataEntity biodataEntity = biodataRepository.findByPersonEntityPersonId(p.getPersonId());
			dto.setIdPerson(p.getPersonId());
			dto.setAddress(p.getAlamat());
			dto.setHp(biodataEntity.getNohp());
			dto.setName(p.getNama());
			dto.setNik(p.getNiKep());
			dto.setTempatLahir(biodataEntity.getTmptLahir());
			dto.setTgl(biodataEntity.getTglLahir());
			dto.setIdBio(biodataEntity.getBioId());
			detailBiodataDtos.add(dto);
		}
		return detailBiodataDtos;
	}

	@GetMapping("/{nik}")
	public List<Object> getByNik(@PathVariable String nik) {
		List<Object> data = new ArrayList<>();
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		StatusMessageDto2 statusMessageDto2 = new StatusMessageDto2();
		if (nik.length() == 16) {
			if (!(personRepository.findByNiKep(nik).isEmpty())) {
				DetailBiodataDto detailBiodataDto = convertToDto(nik);
				statusMessageDto2.setStatus("true");
				statusMessageDto2.setMessage("success");
				statusMessageDto2.setData(detailBiodataDto);
				data.add(statusMessageDto2);
			} else {
				statusMessageDto.setStatus("true");
				statusMessageDto.setMessage("data dengan nik " + nik + " tidak ditemukan");
				data.add(statusMessageDto);
			}
		} else {
			statusMessageDto.setStatus("false");
			statusMessageDto.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			data.add(statusMessageDto);
		}
		return data;
	}
	
    @GetMapping("-id/{idBio}")
    public DetailBiodataDto getBiodata(@PathVariable Integer idBio) {
        BiodataEntity biodataEntity = biodataRepository.findById(idBio).get();
        DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
        // jika tidak pakai model mapper maka perlu setter getter satu satu
        detailBiodataDto.setAddress(biodataEntity.getPersonEntity().getAlamat());
        detailBiodataDto.setHp(biodataEntity.getNohp());
        detailBiodataDto.setIdBio(biodataEntity.getBioId());
        detailBiodataDto.setIdPerson(biodataEntity.getPersonEntity().getPersonId());
        detailBiodataDto.setName(biodataEntity.getPersonEntity().getNama());
        detailBiodataDto.setNik(biodataEntity.getPersonEntity().getNiKep());
        detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
        detailBiodataDto.setTgl(biodataEntity.getTglLahir());
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
		personEntity.setPersonId(dto.getIdPerson());
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
		biodataEntity.setBioId(dto.getIdBio());
		return biodataEntity;
	}

	private DetailBiodataDto convertToDto(String nik) {
		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
		PersonEntity personEntity = personRepository.findByNiKep(nik).get(0);
		BiodataEntity biodataEntity = biodataRepository.findByPersonEntityPersonId(personEntity.getPersonId());

		Calendar cal = Calendar.getInstance();
		cal.setTime(biodataEntity.getTglLahir());

		detailBiodataDto.setAddress(personEntity.getAlamat());
		detailBiodataDto.setHp(biodataEntity.getNohp());
		detailBiodataDto.setName(personEntity.getNama());
		detailBiodataDto.setNik(personEntity.getNiKep());
		detailBiodataDto.setPendidikan_terakhir(pendidikanRepository.pendidikan_terakhir(personEntity.getPersonId()));
		detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
		detailBiodataDto.setTgl(biodataEntity.getTglLahir());
		detailBiodataDto.setUmur(Year.now().getValue() - cal.get(Calendar.YEAR));
		return detailBiodataDto;
	}

//	private DetailBiodataDto convertToDtoFromBiodata(BiodataEntity biodataEntity) {
//		DetailBiodataDto detailBiodataDto = new DetailBiodataDto();
//		detailBiodataDto.setHp(biodataEntity.getNohp());
//		detailBiodataDto.setTgl(biodataEntity.getTglLahir());
//		detailBiodataDto.setTempatLahir(biodataEntity.getTmptLahir());
//		return detailBiodataDto;
//	}
}
