package com.demo.spring.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document(collection = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    private String id;
    private String title;
    private String genre;


    //This is One to One Mapping. One book should have only one Publisher.
    @DBRef
    private Publisher publisher;


//    @DBRef
//    private List<Author> authors;

    // Constructors, getters, setters, etc.
}