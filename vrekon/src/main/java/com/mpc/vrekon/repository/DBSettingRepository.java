package com.mpc.vrekon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpc.vrekon.model.DBSetting;

@Repository
public interface DBSettingRepository extends JpaRepository<DBSetting, Integer>{
	List<DBSetting> findByIdInstitusi(Integer idInstitusi); 
	
	List<DBSetting> findByDbType(String dbType);
	
	List<DBSetting> findByDbTypeOrDbType(String dbType, String orDbType);
}
