package com.project.takebook.mapper;

import com.project.takebook.domain.Rent;
import com.project.takebook.domain.RentDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentMapperTestSuite {

    private RentMapper rentMapper = new RentMapper();

    @Test
    void mapToRentTest() {
        //Given
        RentDto rentDto = new RentDto(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true);

        //When
        Rent mappedRent = rentMapper.mapToRent(rentDto);

        //Then
        assertEquals(Rent.class, mappedRent.getClass());
        assertEquals(1L, mappedRent.getId());
        assertEquals(1L, mappedRent.getBookId());
        assertEquals(1L, mappedRent.getRentedFromId());
        assertEquals(2L, mappedRent.getRentedById());
        assertEquals(LocalDate.of(2022, 6, 14), mappedRent.getRentedFromDate());
        assertEquals(LocalDate.of(2022, 6, 28), mappedRent.getRentedToDate());
        assertTrue(mappedRent.isActive());
    }

    @Test
    void mapToRentDtoTest() {
        //Given
        Rent rent = new Rent(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true);

        //When
        RentDto mappedRentDto = rentMapper.mapToRentDto(rent);

        //Then
        assertEquals(RentDto.class, mappedRentDto.getClass());
        assertEquals(1L, mappedRentDto.getId());
        assertEquals(1L, mappedRentDto.getBookId());
        assertEquals(1L, mappedRentDto.getRentedFromId());
        assertEquals(2L, mappedRentDto.getRentedById());
        assertEquals(LocalDate.of(2022, 6, 14), mappedRentDto.getRentedFromDate());
        assertEquals(LocalDate.of(2022, 6, 28), mappedRentDto.getRentedToDate());
        assertTrue(mappedRentDto.isActive());
    }

    @Test
    void mapToRentDtoListTest() {
        //Given
        ArrayList<Rent> rentList = new ArrayList<>();
        rentList.add(new Rent(1L, 1L, 1L, 2L, LocalDate.of(2022, 6, 14), LocalDate.of(2022, 6, 28), true));

        //When
        List<RentDto> mappedRentDtoList = rentMapper.mapToRentDtoList(rentList);

        //Then
        assertEquals(RentDto.class, mappedRentDtoList.get(0).getClass());
        assertEquals(1, mappedRentDtoList.size());
    }
}
