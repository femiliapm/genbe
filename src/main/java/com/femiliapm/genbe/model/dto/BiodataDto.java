package com.femiliapm.genbe.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BiodataDto {
//	private Integer idBio;
	private String hp;
	
	@JsonFormat(pattern = "dd-MMMM-yyyy")
	private Date tgl;
	
	private String tempatLahir;
//	private Integer idPerson;

//	public Integer getIdBio() {
//		return idBio;
//	}
//
//	public void setIdBio(Integer idBio) {
//		this.idBio = idBio;
//	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public Date getTgl() {
		return tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

//	public Integer getIdPerson() {
//		return idPerson;
//	}
//
//	public void setIdPerson(Integer idPerson) {
//		this.idPerson = idPerson;
//	}
}
