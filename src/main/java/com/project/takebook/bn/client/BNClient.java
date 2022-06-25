package com.project.takebook.bn.client;

import com.project.takebook.bn.config.BNConfig;
import com.project.takebook.domain.BN.BNResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BNClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BNClient.class);

    private final RestTemplate restTemplate;
    private final BNConfig bnConfig;

    public BNResponse getBooksFromBN(String isbnIssn, String author, String title, String genre, String publicationYear, String language) {
        URI url = UriComponentsBuilder.fromHttpUrl(bnConfig.getBnApiEndpoint())
                .queryParam("isbnIssn", isbnIssn)
                .queryParam("author", author)
                .queryParam("title", title)
                .queryParam("genre", genre)
                .queryParam("publicationYear", publicationYear)
                .queryParam("language", language)
                .queryParam("limit", 20)
                .build()
                .encode()
                .toUri();
        //System.out.println(url.toString());
        try {
            BNResponse bnResponse = restTemplate.getForObject(url, BNResponse.class);
            return Optional.ofNullable(bnResponse)
                    .orElse(new BNResponse());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new BNResponse();
        }
    }
}
