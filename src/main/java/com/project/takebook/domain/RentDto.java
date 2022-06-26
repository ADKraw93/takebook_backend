package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private Long bookId;
    private Long rentedFromId;
    private Long rentedById;
    private LocalDate rentedFromDate;
    private LocalDate rentedToDate;
    private boolean isActive;
}
