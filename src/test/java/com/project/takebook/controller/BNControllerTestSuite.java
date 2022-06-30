package com.project.takebook.controller;

import com.google.gson.Gson;
import com.project.takebook.domain.BN.BNResponse;
import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.domain.UserDto;
import com.project.takebook.facade.BNFacade;
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

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(BNController.class)
class BNControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BNFacade bnFacade;

    @Test
    void shouldSearchBooks() throws Exception {
        //Given
        Bibs bibs = new Bibs("isbnIssn", "author", "title", "genre", "publicationYear", "language");
        ArrayList<Bibs> bibsList = new ArrayList<>();
        bibsList.add(bibs);
        BNResponse bnResponse = new BNResponse(bibsList);
        when(bnFacade.getBooksFromBN("isbnIssn", "author", "title", "genre", "publicationYear", "language")).thenReturn(bnResponse);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/bn/search?isbnIssn=isbnIssn&author=author&title=title&genre=genre&publicationYear=publicationYear&language=language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].isbnIssn", Matchers.is("isbnIssn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].author", Matchers.is("author")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].genre", Matchers.is("genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].publicationYear", Matchers.is("publicationYear")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibs[0].language", Matchers.is("language")));
    }

    @Test
    void createBookFromBibs() throws Exception {
        // Given
        Bibs bibs = new Bibs("isbnIssn", "author", "title", "genre", "publicationYear", "language");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bibs);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/bn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
