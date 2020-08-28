package com.femiliapm.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_person")
public class PersonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person", unique = true)
	private Integer personId;
	
	@Column(name = "nik", length = 16, nullable = false, unique = true)
	private String niKep;
	
	@Column(name = "nama", length = 50)
	private String nama;
	
	@Column(name = "alamat")
	private String alamat;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getNiKep() {
		return niKep;
	}

	public void setNiKep(String niKep) {
		this.niKep = niKep;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
}
