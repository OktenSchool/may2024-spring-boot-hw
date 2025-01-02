package org.example.may2024hw.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Fuel {
    private String name;
    private List<String> types;
}
