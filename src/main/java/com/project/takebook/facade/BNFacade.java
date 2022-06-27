package com.project.takebook.facade;

import com.project.takebook.bn.client.BNClient;
import com.project.takebook.domain.BN.BNResponse;
import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.domain.Book;
import com.project.takebook.domain.RestLog;
import com.project.takebook.mapper.BibsMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BNFacade {

    private final DbService service;
    private final BibsMapper bibsMapper;
    private final BNClient bnClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(BNFacade.class);

    private void facadeService(String method, String controller) {
        LOGGER.info("REST request: " + method + " of controller: " + controller + " has been used.");
        service.saveRestLog(new RestLog(
                controller,
                method,
                LocalDate.now()
        ));
    }

    public BNResponse getBooksFromBN(String isbnIssn,
                                     String author,
                                     String title,
                                     String genre,
                                     String publicationYear,
                                     String language) {
        facadeService(SingletonForFacades.SEARCH_BOOKS.getMethod(), SingletonForFacades.SEARCH_BOOKS.getController());
        return bnClient.getBooksFromBN(isbnIssn, author, title, genre, publicationYear, language);
    }

    public void createBookFromBibs(Bibs bibs) {
        facadeService(SingletonForFacades.CREATE_BOOK_FROM_BIBS.getMethod(), SingletonForFacades.CREATE_BOOK_FROM_BIBS.getController());
        Long user_id = 1L;  //docelowo będzie brane id zalogowanego użytkownika, ale jeszcze tego nie zaimplementowano
        Book book = bibsMapper.mapBibsToBook(user_id, bibs);
        service.saveBook(book);
    }
}
