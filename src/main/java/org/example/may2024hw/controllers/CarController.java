package org.example.may2024hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.may2024hw.dto.CarDTO;
import org.example.may2024hw.dto.CarUpdateDTO;
import org.example.may2024hw.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping()
    public ResponseEntity<List<CarDTO>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping
    public ResponseEntity<CarDTO> create(@RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok(carService.create(carDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable(name = "id") Long id, @RequestBody @Valid CarUpdateDTO carUpdateDTO) {
        return ResponseEntity.ok(carService.update(id, carUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
