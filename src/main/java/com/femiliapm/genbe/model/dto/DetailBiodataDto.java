package com.femiliapm.genbe.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetailBiodataDto {
	private Integer idPerson;
	private String nik;
	private String name;
	private String address;
//	private Integer idBio;
	private String hp;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	private Date tgl;

	private String tempatLahir;
	private Integer umur;
	private String pendidikan_terakhir;

	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public Integer getUmur() {
		return umur;
	}

	public void setUmur(Integer umur) {
		this.umur = umur;
	}

	public String getPendidikan_terakhir() {
		return pendidikan_terakhir;
	}

	public void setPendidikan_terakhir(String pendidikan_terakhir) {
		this.pendidikan_terakhir = pendidikan_terakhir;
	}
}
