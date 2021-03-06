package com.project.takebook.service;

import com.project.takebook.controller.BookNotFoundException;
import com.project.takebook.controller.RestLogNotFoundException;
import com.project.takebook.controller.RentNotFoundException;
import com.project.takebook.controller.UserNotFoundException;
import com.project.takebook.domain.Book;
import com.project.takebook.domain.RestLog;
import com.project.takebook.domain.Rent;
import com.project.takebook.domain.User;
import com.project.takebook.repository.BookRepository;
import com.project.takebook.repository.RestLogRepository;
import com.project.takebook.repository.RentRepository;
import com.project.takebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private final RestLogRepository restLogRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUser(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public User saveUser(final User user) {
        return userRepository.save(user);
    }
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }



    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBook(final Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }
    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }
    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }



    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
    public Rent getRent(final Long rentId) throws RentNotFoundException {
        return rentRepository.findById(rentId).orElseThrow(RentNotFoundException::new);
    }
    public Rent saveRent(final Rent rent) {
        return rentRepository.save(rent);
    }
    public void deleteRent(final Long rentId) {
        rentRepository.deleteById(rentId);
    }
    public List<Rent> getOverdued() {
        return rentRepository.findOverduedBook();
    }



    public List<RestLog> getAllLogs() {
        return restLogRepository.findAll();
    }
    public RestLog getRestLog(final Long logId) throws RestLogNotFoundException {
        return restLogRepository.findById(logId).orElseThrow(RestLogNotFoundException::new);
    }
    public RestLog saveRestLog(final RestLog restLog) {
        return restLogRepository.save(restLog);
    }

}
