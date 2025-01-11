package org.example.may2024springhw.repositories;

import org.example.may2024springhw.enteties.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {
}
