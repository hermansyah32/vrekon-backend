package com.mpc.vrekon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpc.vrekon.model.DBTranslate;

@Repository
public interface DBTranslateRepository extends JpaRepository<DBTranslate, Integer>{
	List<DBTranslate> findByIdService(Integer idService);
}
