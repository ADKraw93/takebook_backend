package com.project.takebook.mapper;

import com.project.takebook.domain.Rent;
import com.project.takebook.domain.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getId(),
                rentDto.getBookId(),
                rentDto.getRentedFromId(),
                rentDto.getRentedById(),
                rentDto.getRentedFromDate(),
                rentDto.getRentedToDate(),
                rentDto.isActive()
        );
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getBookId(),
                rent.getRentedFromId(),
                rent.getRentedById(),
                rent.getRentedFromDate(),
                rent.getRentedToDate(),
                rent.isActive()
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
