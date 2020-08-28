package com.femiliapm.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femiliapm.genbe.model.entity.PendidikanEntity;

@Repository
public interface PendidikanRepository extends JpaRepository<PendidikanEntity, Integer>{

}
