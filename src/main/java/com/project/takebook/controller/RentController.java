package com.project.takebook.controller;

import com.project.takebook.domain.RentDto;
import com.project.takebook.facade.RentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentFacade rentFacade;

    @GetMapping
    public ResponseEntity<List<RentDto>> getAllRents() {
        return ResponseEntity.ok(rentFacade.getAllRents());
    }

    @GetMapping("{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return ResponseEntity.ok(rentFacade.getRent(rentId));
    }

    @DeleteMapping("{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) {
        rentFacade.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) {
        return ResponseEntity.ok(rentFacade.updateRent(rentDto));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto) {
            rentFacade.createRent(rentDto);
        return ResponseEntity.ok().build();
    }
}
