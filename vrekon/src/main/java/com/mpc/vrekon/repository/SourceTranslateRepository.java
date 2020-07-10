package com.mpc.vrekon.repository;

import com.mpc.vrekon.model.SourceTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTranslateRepository extends JpaRepository<SourceTranslate, Integer> {
}
