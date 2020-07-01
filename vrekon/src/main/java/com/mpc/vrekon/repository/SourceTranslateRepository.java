package com.mpc.vrekon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTranslateRepository extends JpaRepository<SourceTranslateRepository, Integer> {
}
