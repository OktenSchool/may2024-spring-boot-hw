package org.example.may2024springhw.controllers;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.dto.OwnerDTO;
import org.example.may2024springhw.enteties.Owner;
import org.example.may2024springhw.services.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAll() {
        return ResponseEntity.ok(ownerService.getAll());
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO owner) {
        return ResponseEntity.ok(ownerService.create(owner));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.update(id, owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
