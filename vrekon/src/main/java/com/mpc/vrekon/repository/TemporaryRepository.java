package com.mpc.vrekon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryRepository extends JpaRepository<TemporaryRepository, Integer> {
}
