package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "db_logs")
public class RestLog {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "controller")
    private String controller;

    @Column(name = "method")
    private String method;

    @Column(name = "date")
    private LocalDate date;

    public RestLog(String controller, String method, LocalDate date) {
        this.controller = controller;
        this.method = method;
        this.date = date;
    }
}
