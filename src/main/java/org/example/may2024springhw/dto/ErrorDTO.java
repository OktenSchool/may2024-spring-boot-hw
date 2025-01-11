package org.example.may2024springhw.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    private String details;
    private LocalDateTime timestamp;
}
