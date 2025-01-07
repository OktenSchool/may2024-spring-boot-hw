package org.example.may2024hw.mapper;

import org.example.may2024hw.dto.CarDTO;
import org.example.may2024hw.enteties.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarDTO mapToDTO(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .price(car.getPrice())
                .year(car.getYear())
                .build();
    }

    public Car mapToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setPrice(carDTO.getPrice());
        car.setYear(carDTO.getYear());
        return car;
    }
}
