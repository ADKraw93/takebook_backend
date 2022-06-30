package com.project.takebook.mapper;

import com.project.takebook.domain.Book;
import com.project.takebook.domain.BookDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTestSuite {

    private BookMapper bookMapper = new BookMapper();

    @Test
    void mapToBookTest() {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0);

        //When
        Book mappedBook = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals(Book.class, mappedBook.getClass());
        assertEquals(1L, mappedBook.getId());
        assertEquals(1L, mappedBook.getUser_id());
        assertEquals("author", mappedBook.getAuthor());
        assertEquals("title", mappedBook.getTitle());
        assertEquals("isbnIssn", mappedBook.getIsbnIssn());
        assertEquals("publicationYear", mappedBook.getPublicationYear());
        assertEquals("genre", mappedBook.getGenre());
        assertEquals("language", mappedBook.getLanguage());
        assertEquals(10.0, mappedBook.getDeposit());
    }

    @Test
    void mapToBookDtoTest() {
        //Given
        Book book = new Book(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0);

        //When
        BookDto mappedBookDto = bookMapper.mapToBookDto(book);

        //Then
        assertEquals(BookDto.class, mappedBookDto.getClass());
        assertEquals(1L, mappedBookDto.getId());
        assertEquals(1L, mappedBookDto.getUser_id());
        assertEquals("author", mappedBookDto.getAuthor());
        assertEquals("title", mappedBookDto.getTitle());
        assertEquals("isbnIssn", mappedBookDto.getIsbnIssn());
        assertEquals("publicationYear", mappedBookDto.getPublicationYear());
        assertEquals("genre", mappedBookDto.getGenre());
        assertEquals("language", mappedBookDto.getLanguage());
        assertEquals(10.0, mappedBookDto.getDeposit());
    }

    @Test
    void mapToBookDtoListTest() {
        //Given
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, 1L, "author", "title", "isbnIssn", "publicationYear", "genre", "language", 10.0));

        //When
        List<BookDto> mappedBookDtoList = bookMapper.mapToBookDtoList(bookList);

        //Then
        assertEquals(BookDto.class, mappedBookDtoList.get(0).getClass());
        assertEquals(1, mappedBookDtoList.size());
    }
}
