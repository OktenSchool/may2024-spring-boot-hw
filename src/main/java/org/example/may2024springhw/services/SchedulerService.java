package org.example.may2024springhw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final CarService carService;

    @Scheduled(cron = "0 * * * * *")
    public void sendMaintenanceReminders(){
        carService.notifyOwnersAboutOldMaintenance(2);
    }
}
