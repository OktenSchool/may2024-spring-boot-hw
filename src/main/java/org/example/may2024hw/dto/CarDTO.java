package org.example.may2024hw.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;

    @NotBlank
    private String brand;

    @Min(value = 0, message = "price min 0")
    @Max(value = 1_000_000, message = "price max 1 000 000")
    private int price;

    @Min(value = 1990, message = "year min 1990")
    @Max(value =2024, message = "year max 2024")
    private int year;
}
