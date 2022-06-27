package com.project.takebook.controller;

import com.project.takebook.domain.BN.BNResponse;
import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.facade.BNFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bn")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BNController {

    private final BNFacade bnFacade;

    @GetMapping("search")
    public ResponseEntity<BNResponse> searchBooks(@RequestParam(required = false) String isbnIssn,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String genre,
                                                  @RequestParam(required = false) String publicationYear,
                                                  @RequestParam(required = false) String language) {

        return ResponseEntity.ok(bnFacade.getBooksFromBN(isbnIssn, author, title, genre, publicationYear, language));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBookFromBibs(@RequestBody Bibs bibs) {
        bnFacade.createBookFromBibs(bibs);
        return ResponseEntity.ok().build();
    }
}
