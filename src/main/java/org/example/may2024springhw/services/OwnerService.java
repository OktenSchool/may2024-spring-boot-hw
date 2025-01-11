package org.example.may2024springhw.services;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.dto.OwnerDTO;
import org.example.may2024springhw.enteties.Owner;
import org.example.may2024springhw.mappers.OwnerMapper;
import org.example.may2024springhw.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public List<OwnerDTO> getAll() {
        return ownerRepository.findAll().stream().map(ownerMapper::toOwnerDTO).toList();
    }

    public OwnerDTO create(OwnerDTO owner) {
        if (ownerRepository.findByUsername(owner.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Owner with this username already exists");
        }

        if (ownerRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Owner with this email already exists");
        }

        Owner save = ownerRepository.save(ownerMapper.toOwner(owner));
        return ownerMapper.toOwnerDTO(save);
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Owner with this id does not exist"));
    }

    public Owner update(Long id, Owner owner) {
        Owner existing = getById(id);
        existing.setUsername(owner.getUsername());
        existing.setEmail(owner.getEmail());
        return ownerRepository.save(existing);
    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
}
