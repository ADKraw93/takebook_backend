package com.project.takebook.bn.client;

import com.project.takebook.bn.config.BNConfig;
import com.project.takebook.domain.BN.BNResponse;
import com.project.takebook.domain.BN.Bibs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BNClientTestSuite {

    @InjectMocks
    private BNClient bnClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BNConfig bnConfig;

    @Test
    void getBooksFromBNTest() {
        //Given
        List<Bibs> bibsList = new ArrayList<>();
        bibsList.add(new Bibs("isbnIssn", "author", "title", "genre", "publicationYear", "language"));
        BNResponse mockResponse = new BNResponse(bibsList);
        URI url = UriComponentsBuilder.fromHttpUrl("https://data.bn.org.pl/api/institutions/bibs.json")
                .queryParam("isbnIssn", "isbnIssn")
                .queryParam("author", "author")
                .queryParam("title", "title")
                .queryParam("genre", "genre")
                .queryParam("publicationYear", "publicationYear")
                .queryParam("language", "language")
                .queryParam("limit", 20)
                .build()
                .encode()
                .toUri();
        when(restTemplate.getForObject(url, BNResponse.class)).thenReturn(mockResponse);
        when(bnConfig.getBnApiEndpoint()).thenReturn("https://data.bn.org.pl/api/institutions/bibs.json");

        //When
        BNResponse response = bnClient.getBooksFromBN("isbnIssn", "author", "title", "genre", "publicationYear", "language");

        //Then
        assertEquals(BNResponse.class, response.getClass());
        assertEquals(1, response.getBibs().size());
    }
}
