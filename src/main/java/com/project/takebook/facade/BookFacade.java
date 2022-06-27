package com.project.takebook.facade;

import com.project.takebook.controller.BookNotFoundException;
import com.project.takebook.domain.RestLog;
import com.project.takebook.domain.Book;
import com.project.takebook.domain.BookDto;
import com.project.takebook.mapper.BookMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookFacade {

    private final DbService service;
    private final BookMapper bookMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookFacade.class);

    private void facadeService(String method, String controller) {
        LOGGER.info("REST request: " + method + " of controller: " + controller + " has been used.");
        service.saveRestLog(new RestLog(
                controller,
                method,
                LocalDate.now()
        ));
    }

    public List<BookDto> getAllBooks() {
        facadeService(SingletonForFacades.GET_ALL_BOOKS.getMethod(), SingletonForFacades.GET_ALL_BOOKS.getController());
        List<Book> books = service.getAllBooks();
        return bookMapper.mapToBookDtoList(books);
    }

    public BookDto getBook(Long bookId) throws BookNotFoundException {
        facadeService(SingletonForFacades.GET_BOOK.getMethod(), SingletonForFacades.GET_BOOK.getController());
        return bookMapper.mapToBookDto(service.getBook(bookId));
    }

    public void deleteBook(Long bookId) {
        facadeService(SingletonForFacades.DELETE_BOOK.getMethod(), SingletonForFacades.DELETE_BOOK.getController());
        service.deleteBook(bookId);
    }

    public BookDto updateBook(BookDto bookDto) {
        facadeService(SingletonForFacades.UPDATE_BOOK.getMethod(), SingletonForFacades.UPDATE_BOOK.getController());
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = service.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    public void createBook(BookDto bookDto) {
        facadeService(SingletonForFacades.CREATE_BOOK.getMethod(), SingletonForFacades.CREATE_BOOK.getController());
        Book book = bookMapper.mapToBook(bookDto);
        service.saveBook(book);
    }
}
