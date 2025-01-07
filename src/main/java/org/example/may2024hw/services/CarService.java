package org.example.may2024hw.services;

import lombok.RequiredArgsConstructor;
import org.example.may2024hw.dto.CarDTO;
import org.example.may2024hw.dto.CarUpdateDTO;
import org.example.may2024hw.enteties.Car;
import org.example.may2024hw.mapper.CarMapper;
import org.example.may2024hw.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDTO> getAll(){
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO create(CarDTO carDTO){
        Car car = carRepository.save(carMapper.mapToCar(carDTO));
        return carMapper.mapToDTO(car);
    }

    public CarDTO getById(Long id){
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car not found"));
        return carMapper.mapToDTO(car);
    }

    public CarDTO update(Long id, CarUpdateDTO carUpdateDTO){
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car not found"));

        car.setBrand(carUpdateDTO.getBrand());
        car.setPrice(carUpdateDTO.getPrice());
        car.setYear(carUpdateDTO.getYear());
        Car savedCar = carRepository.save(car);
        return carMapper.mapToDTO(savedCar);
    }

    public void delete(Long id){
        carRepository.deleteById(id);
    }
}
