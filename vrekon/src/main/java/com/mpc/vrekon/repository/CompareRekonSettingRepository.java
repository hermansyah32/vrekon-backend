package com.mpc.vrekon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpc.vrekon.model.CompareRekonSetting;

@Repository
public interface CompareRekonSettingRepository extends JpaRepository<CompareRekonSetting, Integer>{
	
}
