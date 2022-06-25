package com.project.takebook.repository;

import com.project.takebook.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    Book save(Book book);
    Optional<Book> findById(Long id);
    void deleteById(Long id);
}
