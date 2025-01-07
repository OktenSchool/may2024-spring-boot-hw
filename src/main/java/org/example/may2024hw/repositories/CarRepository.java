package org.example.may2024hw.repositories;

import org.example.may2024hw.enteties.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
