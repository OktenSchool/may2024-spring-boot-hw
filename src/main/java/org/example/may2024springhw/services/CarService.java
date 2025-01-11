package org.example.may2024springhw.services;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.dto.CarDTO;
import org.example.may2024springhw.dto.CarUpdateDTO;
import org.example.may2024springhw.enteties.Car;
import org.example.may2024springhw.enteties.Owner;
import org.example.may2024springhw.mappers.CarMapper;
import org.example.may2024springhw.repositories.CarRepository;
import org.example.may2024springhw.repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final OwnerRepository ownerRepository;
    private final EmailService emailService;

    public List<CarDTO> getAll() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO create(CarDTO carDTO) {
        Owner owner = ownerRepository.findByUsername(carDTO.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));

        Car car = carMapper.mapToCar(carDTO);
        car.setOwner(owner);
        car = carRepository.save(car);
        return carMapper.mapToDTO(car);
    }

    public CarDTO getById(Long id) {
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car not found"));
        return carMapper.mapToDTO(car);
    }

    public CarDTO update(Long id, CarUpdateDTO carUpdateDTO) {
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car not found"));

        car.setBrand(carUpdateDTO.getBrand());
        car.setPrice(carUpdateDTO.getPrice());
        car.setYear(carUpdateDTO.getYear());
        Car savedCar = carRepository.save(car);
        return carMapper.mapToDTO(savedCar);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void notifyOwnersAboutOldMaintenance(int day) {
        LocalDate date = LocalDate.now().minusDays(day);

        ownerRepository.findAll().forEach(owner -> {
            List<Car> oldMaintenanceCars
                    = owner.getCars().stream()
                    .filter(car -> car.getLastMaintenanceTimestamp().isBefore(date)).toList();

            if (!oldMaintenanceCars.isEmpty()) {
                String subject = "Cars with old maintenance date:";
                String text = "Your cars needed maintenance:\n" +
                        oldMaintenanceCars.stream()
                                .map(car -> String.format(
                                        "Car: %s, Last Maintenance: %s",
                                        car.getBrand(),
                                        car.getLastMaintenanceTimestamp()
                                )).collect(Collectors.joining("\n"));
                emailService.sendEmail(owner.getEmail(), subject, text);
            }
        });


    }
}
