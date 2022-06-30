package com.project.takebook.mapper;

import com.project.takebook.domain.User;
import com.project.takebook.domain.UserDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTestSuite {

    private UserMapper userMapper = new UserMapper();

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(1L, "John", "john@kodilla.com", "pass123", 50.0);

        //When
        User mappedUser = userMapper.mapToUser(userDto);

        //Then
        assertEquals(User.class, mappedUser.getClass());
        assertEquals(1L, mappedUser.getId());
        assertEquals("John", mappedUser.getUsername());
        assertEquals("john@kodilla.com", mappedUser.getEmail());
        assertEquals("pass123", mappedUser.getPassword());
        assertEquals(50.0, mappedUser.getBudget());
    }

    @Test
    void mapToUserDtoTest() {
        //Given
        User user = new User(1L, "John", "john@kodilla.com", "pass123", 50.0);

        //When
        UserDto mappedUserDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(UserDto.class, mappedUserDto.getClass());
        assertEquals(1L, mappedUserDto.getId());
        assertEquals("John", mappedUserDto.getUsername());
        assertEquals("john@kodilla.com", mappedUserDto.getEmail());
        assertEquals("pass123", mappedUserDto.getPassword());
        assertEquals(50.0, mappedUserDto.getBudget());
    }

    @Test
    void mapToUserDtoListTest() {
        //Given
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "john@kodilla.com", "pass123", 50.0));

        //When
        List<UserDto> mappedUserDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals(UserDto.class, mappedUserDtoList.get(0).getClass());
        assertEquals(1, mappedUserDtoList.size());
    }
}
