package com.femiliapm.genbe.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.femiliapm.genbe.model.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{
//	@Query(value = "SELECT * FROM t_biodata AS tb INNER JOIN t_person AS tp ON tb.idperson = tp.id_person")
//		List<PersonEntity> fullBiodata();
}
