package com.project.takebook.repository;

import com.project.takebook.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    List<Rent> findAll();
    Rent save(Rent rent);
    Optional<Rent> findById(Long id);
    void deleteById(Long id);
}
