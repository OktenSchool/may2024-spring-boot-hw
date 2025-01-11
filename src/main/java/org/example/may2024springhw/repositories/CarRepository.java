package org.example.may2024springhw.repositories;

import org.example.may2024springhw.enteties.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
