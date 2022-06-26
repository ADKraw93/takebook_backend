package com.project.takebook.mapper;

import com.project.takebook.domain.BN.Bibs;
import com.project.takebook.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BibsMapper {
    public Book mapBibsToBook(final Long user_id, final Bibs bibs) {
        return new Book(
                user_id,
                bibs.getAuthor(),
                bibs.getTitle(),
                bibs.getIsbnIssn(),
                bibs.getPublicationYear(),
                bibs.getGenre(),
                bibs.getLanguage()
        );
    }
}
