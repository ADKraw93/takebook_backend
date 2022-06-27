package com.project.takebook.facade;

import com.project.takebook.controller.RentNotFoundException;
import com.project.takebook.domain.RestLog;
import com.project.takebook.domain.Rent;
import com.project.takebook.domain.RentDto;
import com.project.takebook.mapper.RentMapper;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RentFacade {

    private final DbService service;
    private final RentMapper rentMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RentFacade.class);

    private void facadeService(String method, String controller) {
        LOGGER.info("REST request: " + method + " of controller: " + controller + " has been used.");
        service.saveRestLog(new RestLog(
                controller,
                method,
                LocalDate.now()
        ));
    }

    public List<RentDto> getAllRents() {
        facadeService(SingletonForFacades.GET_ALL_RENTS.getMethod(), SingletonForFacades.GET_ALL_RENTS.getController());
        List<Rent> rents = service.getAllRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    public RentDto getRent(Long rentId) throws RentNotFoundException {
        facadeService(SingletonForFacades.GET_RENT.getMethod(), SingletonForFacades.GET_RENT.getController());
        return rentMapper.mapToRentDto(service.getRent(rentId));
    }

    public void deleteRent(Long rentId) {
        facadeService(SingletonForFacades.DELETE_RENT.getMethod(), SingletonForFacades.DELETE_RENT.getController());
        service.deleteRent(rentId);
    }

    public RentDto updateRent(RentDto rentDto) {
        facadeService(SingletonForFacades.UPDATE_RENT.getMethod(), SingletonForFacades.UPDATE_RENT.getController());
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent savedRent = service.saveRent(rent);
        return rentMapper.mapToRentDto(savedRent);
    }

    public void createRent(RentDto rentDto) {
        facadeService(SingletonForFacades.CREATE_RENT.getMethod(), SingletonForFacades.CREATE_RENT.getController());
        Rent rent = rentMapper.mapToRent(rentDto);
        service.saveRent(rent);
    }
}
