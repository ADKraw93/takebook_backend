package com.project.takebook.controller;

import com.project.takebook.domain.Rent;
import com.project.takebook.domain.RentDto;
import com.project.takebook.mapper.RentMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rents")
@RequiredArgsConstructor
public class RentController {

    private final DbService service;
    private final RentMapper rentMapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents() {
        List<Rent> rents = service.getAllRents();
        return ResponseEntity.ok(rentMapper.mapToRentDtoList(rents));
    }

    @GetMapping("{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
            return ResponseEntity.ok(rentMapper.mapToRentDto(service.getRent(rentId)));
    }

    @DeleteMapping("{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) {
        service.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent savedRent = service.saveRent(rent);
        return ResponseEntity.ok(rentMapper.mapToRentDto(savedRent));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        service.saveRent(rent);
        return ResponseEntity.ok().build();
    }
}
