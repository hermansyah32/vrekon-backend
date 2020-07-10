package com.mpc.vrekon.repository;

import com.mpc.vrekon.model.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceConfigRepository extends JpaRepository<SourceConfig, Integer> {
    List<SourceConfig> findByIdApplication(Integer idApplication);

    List<SourceConfig> findBySourceType(String dbType);

    List<SourceConfig> findBySourceTypeOrSourceType(String sourceType, String orSourceType);
}
