package com.project.takebook.mapper;

import com.project.takebook.domain.Book;
import com.project.takebook.domain.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {
    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getUser_id(),
                bookDto.getAuthor(),
                bookDto.getTitle(),
                bookDto.getIsbnIssn(),
                bookDto.getPublicationYear(),
                bookDto.getGenre(),
                bookDto.getLanguage(),
                bookDto.getDeposit()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getUser_id(),
                book.getAuthor(),
                book.getTitle(),
                book.getIsbnIssn(),
                book.getPublicationYear(),
                book.getGenre(),
                book.getLanguage(),
                book.getDeposit()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
