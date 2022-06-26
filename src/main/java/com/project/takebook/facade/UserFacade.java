package com.project.takebook.facade;

import com.project.takebook.controller.UserNotFoundException;
import com.project.takebook.domain.RestLog;
import com.project.takebook.domain.User;
import com.project.takebook.domain.UserDto;
import com.project.takebook.mapper.UserMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final DbService service;
    private final UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacade.class);

    private void facadeService(String method, String controller) {
        LOGGER.info("REST request: " + method + " of controller: " + controller + " has been used.");
        service.saveRestLog(new RestLog(
                controller,
                method,
                LocalDate.now()
        ));
    }

    public List<UserDto> getAllUsers() {
        facadeService(SingletonForFacades.GET_ALL_USERS.getMethod(), SingletonForFacades.GET_ALL_USERS.getController());
        List<User> users = service.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUser(Long userId) throws UserNotFoundException {
        facadeService(SingletonForFacades.GET_USER.getMethod(), SingletonForFacades.GET_USER.getController());
        return userMapper.mapToUserDto(service.getUser(userId));
    }

    public void deleteUser(Long userId) {
        facadeService(SingletonForFacades.DELETE_USER.getMethod(), SingletonForFacades.DELETE_USER.getController());
        service.deleteUser(userId);
    }

    public UserDto updateUser(UserDto userDto) {
        facadeService(SingletonForFacades.UPDATE_USER.getMethod(), SingletonForFacades.UPDATE_USER.getController());
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }

    public void createUser(UserDto userDto) {
        facadeService(SingletonForFacades.CREATE_USER.getMethod(), SingletonForFacades.CREATE_USER.getController());
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
    }
}
