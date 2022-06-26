package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rents")
@NamedNativeQuery(
        name = "Rent.findOverduedBook",
        query = "SELECT * FROM rents" +
                " WHERE is_active=true AND rented_to_date < NOW()",
        resultClass = Rent.class
)
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "rentedFromId")
    private Long rentedFromId;

    @Column(name = "rentedById")
    private Long rentedById;

    @Column(name = "rentedFromDate")
    private LocalDate rentedFromDate;

    @Column(name = "rentedToDate")
    private LocalDate rentedToDate;

    @Column(name = "isActive")
    private boolean isActive;
}
