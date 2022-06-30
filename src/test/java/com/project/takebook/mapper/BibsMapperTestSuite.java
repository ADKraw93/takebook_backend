package com.project.takebook.mapper;

import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibsMapperTestSuite {

    BibsMapper bibsMapper = new BibsMapper();

    @Test
    void mapBibsToBookTest() {
        //Given
        Bibs bibs = new Bibs("isbnIssn", "author", "title", "genre", "publicationYear", "language");

        //When
        Book book = bibsMapper.mapBibsToBook(1L, bibs);

        //Then
        assertEquals(Book.class, book.getClass());
        assertEquals(1L, book.getUser_id());
        assertEquals("isbnIssn", book.getIsbnIssn());
        assertEquals("author", book.getAuthor());
        assertEquals("title", book.getTitle());
        assertEquals("genre", book.getGenre());
        assertEquals("publicationYear", book.getPublicationYear());
        assertEquals("language", book.getLanguage());
        assertEquals(0, book.getDeposit());
    }
}
