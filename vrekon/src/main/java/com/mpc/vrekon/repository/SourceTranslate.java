package com.mpc.vrekon.repository;

import com.mpc.vrekon.model.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTranslate extends JpaRepository<SourceTranslate, Integer> {
}
