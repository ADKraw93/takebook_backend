package com.project.takebook.controller;

import com.project.takebook.bn.client.BNClient;
import com.project.takebook.domain.BN.BNResponse;
import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.domain.Book;
import com.project.takebook.domain.BookDto;
import com.project.takebook.mapper.BibsMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bn")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BNController {

    private final DbService service;
    private final BNClient bnClient;
    private final BibsMapper bibsMapper;

    @GetMapping("search")
    public ResponseEntity<BNResponse> searchBooks(@RequestParam(required = false) String isbnIssn,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String genre,
                                                  @RequestParam(required = false) String publicationYear,
                                                  @RequestParam(required = false) String language) {

        return ResponseEntity.ok(bnClient.getBooksFromBN(isbnIssn, author, title, genre, publicationYear, language));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBookFromBibs(@RequestBody Bibs bibs) {
        Long user_id = 1L;  //docelowo będzie brane id zalogowanego użytkownika, ale jeszcze tego nie zaimplementowano
        Book book = bibsMapper.mapBibsToBook(user_id, bibs);
        service.saveBook(book);
        return ResponseEntity.ok().build();
    }
}
