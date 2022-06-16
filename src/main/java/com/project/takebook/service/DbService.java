package com.project.takebook.service;

import com.project.takebook.domain.User;
import com.project.takebook.repository.BookRepository;
import com.project.takebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
