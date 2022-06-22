package com.project.takebook.controller;

import com.project.takebook.bn.client.BNClient;
import com.project.takebook.domain.BN.BNResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bn")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BNController {
    private final BNClient bnClient;

    @GetMapping("search")
    public ResponseEntity<BNResponse> searchBooks(@RequestParam(required = false) String isbnIssn,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String genre,
                                                  @RequestParam(required = false) String publicationYear,
                                                  @RequestParam(required = false) String language) {
        //System.out.println("isbnIssn: " + isbnIssn + "author: " + author + "title: " + title + "genre: " + genre + "publicationYear: " + publicationYear + "language: " + language);
        return ResponseEntity.ok(bnClient.getBooksFromBN(isbnIssn, author, title, genre, publicationYear, language));
    }
}
