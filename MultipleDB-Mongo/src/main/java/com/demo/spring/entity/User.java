package com.demo.spring.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;

    // Getters and setters
}
