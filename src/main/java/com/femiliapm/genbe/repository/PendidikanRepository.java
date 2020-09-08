package com.femiliapm.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.femiliapm.genbe.model.entity.PendidikanEntity;

@Repository
public interface PendidikanRepository extends JpaRepository<PendidikanEntity, Integer> {
	@Query(value = "select tp.jenjang from t_pendidikan tp where idperson = ?\r\n"
			+ "order by tp.tahunlulus desc limit 1", nativeQuery = true)
	String pendidikan_terakhir(Integer idPerson);
}
