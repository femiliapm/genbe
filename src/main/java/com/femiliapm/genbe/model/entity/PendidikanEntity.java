package com.femiliapm.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pendidikan")
public class PendidikanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pendidikan")
	private Integer pendidikanId;
	
	@ManyToOne
	@JoinColumn(name = "idperson", nullable = false)
	private PersonEntity personEntity;
	
	@Column(name = "jenjang", length = 10, nullable = false)
	private String level;
	
	@Column(name = "institusi", length = 50, nullable = false)
	private String institution;
	
	@Column(name = "tahunmasuk", length = 10, nullable = false)
	private String thnMasuk;
	
	@Column(name = "tahunlulus", length = 10, nullable = false)
	private String thnLulus;

	public Integer getPendidikanId() {
		return pendidikanId;
	}

	public void setPendidikanId(Integer pendidikanId) {
		this.pendidikanId = pendidikanId;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getThnMasuk() {
		return thnMasuk;
	}

	public void setThnMasuk(String thnMasuk) {
		this.thnMasuk = thnMasuk;
	}

	public String getThnLulus() {
		return thnLulus;
	}

	public void setThnLulus(String thnLulus) {
		this.thnLulus = thnLulus;
	}
}
