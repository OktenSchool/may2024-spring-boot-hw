package org.example.may2024springhw.services;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.enteties.Maintenance;
import org.example.may2024springhw.repositories.MaintenanceRepository;
import org.example.may2024springhw.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final OwnerRepository ownerRepository;
    private final EmailService emailService;

    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    public Maintenance create(Maintenance maintenance) {
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

        String subject = "Added new maintenance";
        String text = String.format(
                "Added new maintenance: %s, Description: %s, Price: %d",
                maintenance.getName(),
                maintenance.getDescription(),
                maintenance.getPrice()
        );

        ownerRepository.findAll().forEach(owner -> {
            emailService.sendEmail(owner.getEmail(), subject, text);
        });

        return savedMaintenance;
    }

    public Maintenance getById(String id) {
        return maintenanceRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Maintenance not found"));
    }
    public Maintenance update(String id, Maintenance maintenance) {
        Maintenance existing = getById(id);
        existing.setName(maintenance.getName());
        existing.setDescription(maintenance.getDescription());
        existing.setPrice(maintenance.getPrice());
        return maintenanceRepository.save(existing);
    }

    public void delete(String id) {
        maintenanceRepository.deleteById(id);
    }
}
