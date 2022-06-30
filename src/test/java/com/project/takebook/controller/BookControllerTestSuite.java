package com.project.takebook.controller;

import com.google.gson.Gson;
import com.project.takebook.domain.BookDto;
import com.project.takebook.facade.BookFacade;
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
@WebMvcTest(BookController.class)
class BookControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookFacade bookFacade;

    @Test
    void shouldGetBooks() throws Exception {
        //Given
        List<BookDto> booksDto = List.of(new BookDto(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0));
        when(bookFacade.getAllBooks()).thenReturn(booksDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user_id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbnIssn", Matchers.is("isbnIssn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].publicationYear", Matchers.is("publicationYear")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre", Matchers.is("genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].language", Matchers.is("language")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].deposit", Matchers.is(10.0)));
    }

    @Test
    void shouldGetBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0);
        when(bookFacade.getBook(any(Long.class))).thenReturn(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbnIssn", Matchers.is("isbnIssn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear", Matchers.is("publicationYear")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre", Matchers.is("genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language", Matchers.is("language")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deposit", Matchers.is(10.0)));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        // Given
        BookDto bookDto = new BookDto(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0);
        when(bookFacade.updateBook(any(BookDto.class))).thenReturn(bookDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbnIssn", Matchers.is("isbnIssn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear", Matchers.is("publicationYear")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre", Matchers.is("genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language", Matchers.is("language")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deposit", Matchers.is(10.0)));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        BookDto bookDto = new BookDto(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
