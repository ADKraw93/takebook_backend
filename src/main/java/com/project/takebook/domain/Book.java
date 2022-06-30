package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "isbnIssn")
    private String isbnIssn;

    @Column(name = "publicationYear")
    private String publicationYear;

    @Column(name = "genre")
    private String genre;

    @Column(name = "language")
    private String language;

    @Column(name = "deposit")
    private double deposit;

    public Book(Long user_id, String author, String title, String isbnIssn, String publicationYear, String genre, String language) {
        this.user_id = user_id;
        this.author = author;
        this.title = title;
        this.isbnIssn = isbnIssn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.language = language;
        this.deposit = 0.0;
    }
}
