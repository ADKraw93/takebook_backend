package com.project.takebook.service;

import com.project.takebook.controller.UserNotFoundException;
import com.project.takebook.domain.User;
import com.project.takebook.repository.BookRepository;
import com.project.takebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final UserRepository userRepository;
    //private final BookRepository bookRepository;

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
}
