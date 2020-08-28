package com.femiliapm.genbe.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_biodata")
public class BiodataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bio", unique = true)
	private Integer bioId;

	@Column(name = "nohp", length = 16)
	private String nohp;

	@Column(name = "tanggal_lahir", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date tglLahir;

	@Column(name = "tempat_lahir", length = 50)
	private String tmptLahir;

	@OneToOne
	@JoinColumn(name = "idperson", unique = true, nullable = false)
	private PersonEntity personEntity;

	public Integer getBioId() {
		return bioId;
	}

	public void setBioId(Integer bioId) {
		this.bioId = bioId;
	}

	public String getNohp() {
		return nohp;
	}

	public void setNohp(String nohp) {
		this.nohp = nohp;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	public String getTmptLahir() {
		return tmptLahir;
	}

	public void setTmptLahir(String tmptLahir) {
		this.tmptLahir = tmptLahir;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}
}
