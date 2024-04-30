package com.demo.spring.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection ="Author")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Author {

    @Id
    private String id;
    private String name;

    //This is One to One Mapping.One Author can write only one book
    @DBRef
    private Book book;

    // Constructors, getters, setters, etc.
}
