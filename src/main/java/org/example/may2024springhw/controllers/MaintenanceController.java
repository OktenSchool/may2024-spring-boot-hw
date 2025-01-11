package org.example.may2024springhw.controllers;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.enteties.Maintenance;
import org.example.may2024springhw.services.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAll(){
        return ResponseEntity.ok(maintenanceService.getAll());
    }

    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance){
        return ResponseEntity.ok(maintenanceService.create(maintenance));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getById(@PathVariable String id){
        return ResponseEntity.ok(maintenanceService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(@PathVariable String id, @RequestBody Maintenance maintenance){
        return ResponseEntity.ok(maintenanceService.update(id, maintenance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Maintenance> delete(@PathVariable String id){
        maintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
