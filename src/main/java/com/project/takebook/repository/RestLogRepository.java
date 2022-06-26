package com.project.takebook.repository;

import com.project.takebook.domain.RestLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestLogRepository extends CrudRepository<RestLog, Long> {
    List<RestLog> findAll();
    RestLog save(RestLog restLog);
    Optional<RestLog> findById(Long id);
    Optional<RestLog> findByDate(LocalDate date);
}
