package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private Long user_id;
    private String author;
    private String title;
    private String isbnIssn;
    private String publicationYear;
    private String genre;
    private String language;
    private double deposit;
}
