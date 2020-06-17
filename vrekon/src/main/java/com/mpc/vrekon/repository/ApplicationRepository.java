package com.mpc.vrekon.repository;

import com.mpc.vrekon.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
