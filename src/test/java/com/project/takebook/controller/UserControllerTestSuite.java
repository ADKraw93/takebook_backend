package com.project.takebook.controller;

import com.google.gson.Gson;
import com.project.takebook.domain.User;
import com.project.takebook.domain.UserDto;
import com.project.takebook.facade.UserFacade;
import com.project.takebook.mapper.UserMapper;
import com.project.takebook.service.DbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Test
    void shouldGetUsers() throws Exception {
        //Given
        List<UserDto> usersDto = List.of(new UserDto(1L, "John", "john@kodilla.com", "pass123", 50.0));
        when(userFacade.getAllUsers()).thenReturn(usersDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("john@kodilla.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("pass123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].budget", Matchers.is(50.0)));
    }

    @Test
    void shouldGetUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "John", "john@kodilla.com", "pass123", 50.0);
        when(userFacade.getUser(any(Long.class))).thenReturn(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("john@kodilla.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("pass123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget", Matchers.is(50.0)));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        // Given
        UserDto userDto = new UserDto(1L, "John", "john@kodilla.com", "pass123", 50.0);
        when(userFacade.updateUser(any(UserDto.class))).thenReturn(userDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("john@kodilla.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("pass123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget", Matchers.is(50.0)));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        UserDto userDto = new UserDto(1L, "John", "john@kodilla.com", "pass123", 50.0);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
