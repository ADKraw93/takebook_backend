package com.project.takebook.scheudler;

import com.project.takebook.controller.UserNotFoundException;
import com.project.takebook.domain.Rent;
import com.project.takebook.domain.User;
import com.project.takebook.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OverduedScheduler {
    private final DbService service;

    @Scheduled(cron = "0 0 7 * * *")
    public void payFineForDelays() throws UserNotFoundException {
        List<Rent> overdued = service.getOverdued();
        overdued.stream()
                .forEach(rent -> {
                    try{
                        User user = service.getUser(rent.getRentedById());
                        if(user.getBudget()-1.0 >= 0) {
                            user.setBudget(user.getBudget() - 1.0);
                        } else {
                            user.setBudget(0);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });
    }
}
