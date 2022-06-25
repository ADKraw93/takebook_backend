package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private Long bookId;
    private Long rentedFromId;
    private Long rentedById;
    private Date rentedFromDate;
    private Date rentedToDate;
    private boolean isActive;
}
