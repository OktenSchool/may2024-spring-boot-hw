package org.example.may2024springhw.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import org.example.may2024springhw.enteties.Car;

import java.util.List;

@Data
@Builder
public class OwnerDTO {
    private Long id;
    private String username;
    private String email;
    private List<CarDTO> cars;
}
