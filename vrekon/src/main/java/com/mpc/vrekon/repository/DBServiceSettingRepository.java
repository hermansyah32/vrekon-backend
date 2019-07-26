package com.mpc.vrekon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpc.vrekon.model.DBServiceSetting;

public interface DBServiceSettingRepository extends JpaRepository<DBServiceSetting, Integer>{
	List<DBServiceSetting> findByIdInstitusi(Integer idInstitusi);  
}
