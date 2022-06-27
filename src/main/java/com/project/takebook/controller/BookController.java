package com.project.takebook.controller;

import com.project.takebook.domain.Book;
import com.project.takebook.domain.BookDto;
import com.project.takebook.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookFacade.getAllBooks());
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookFacade.getBook(bookId));
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookFacade.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookFacade.updateBook(bookDto));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        bookFacade.createBook(bookDto);
        return ResponseEntity.ok().build();
    }
}
