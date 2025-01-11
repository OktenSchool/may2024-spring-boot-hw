package org.example.may2024springhw.mappers;

import lombok.RequiredArgsConstructor;
import org.example.may2024springhw.dto.OwnerDTO;
import org.example.may2024springhw.enteties.Owner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final CarMapper carMapper;

    public OwnerDTO toOwnerDTO(Owner owner) {
        return OwnerDTO.builder()
                .id(owner.getId())
                .email(owner.getEmail())
                .username(owner.getUsername())
                .cars(owner.getCars().stream().map(carMapper::mapToDTO).toList())
                .build();
    }

    public Owner toOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setCars(new ArrayList<>());
        owner.setEmail(ownerDTO.getEmail());
        owner.setUsername(ownerDTO.getUsername());
        return owner;
    }
}
