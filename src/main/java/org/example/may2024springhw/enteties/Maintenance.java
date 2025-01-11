package org.example.may2024springhw.enteties;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Maintenance {
    @Id
    private String id;
    private String name;
    private String description;
    private int price;
}
