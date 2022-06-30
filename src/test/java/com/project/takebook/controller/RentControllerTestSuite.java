package com.project.takebook.controller;

import com.google.gson.Gson;
import com.project.takebook.domain.RentDto;
import com.project.takebook.facade.RentFacade;
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

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RentController.class)
class RentControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentFacade rentFacade;

    @Test
    void shouldGetRents() throws Exception {
        //Given
        List<RentDto> rentsDto = List.of(new RentDto(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true));
        when(rentFacade.getAllRents()).thenReturn(rentsDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentedFromId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentedById", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentedFromDate", Matchers.is("2022-06-14")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentedToDate", Matchers.is("2022-06-28")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)));
    }

    @Test
    void shouldGetRent() throws Exception {
        //Given
        RentDto rentDto = new RentDto(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true);
        when(rentFacade.getRent(any(Long.class))).thenReturn(rentDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedFromId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedById", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedFromDate", Matchers.is("2022-06-14")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedToDate", Matchers.is("2022-06-28")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    void shouldDeleteRent() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/rents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateRent() throws Exception {  //tutaj adapter jeszcze trzeba zrobić na LocalDate
        // Given
        RentDto rentDto = new RentDto(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true);
        when(rentFacade.updateRent(any(RentDto.class))).thenReturn(rentDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedFromId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedById", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedFromDate", Matchers.is("2022-06-14")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentedToDate", Matchers.is("2022-06-28")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    void shouldCreateTask() throws Exception { //tutaj adapter jeszcze trzeba zrobić na LocalDate
        // Given
        RentDto rentDto = new RentDto(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);
        System.out.println(jsonContent);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
