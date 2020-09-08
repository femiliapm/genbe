package com.femiliapm.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femiliapm.genbe.model.entity.BiodataEntity;

@Repository
public interface BiodataRepository extends JpaRepository<BiodataEntity, Integer>{
	BiodataEntity findByPersonEntityPersonId(Integer idPerson);
}
