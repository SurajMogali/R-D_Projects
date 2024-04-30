package com.demo.spring.entity;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection ="Publisher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    private String id;
    private String name;
    private String location;


    //This refers to Many to One Relationship. Many books can be published by one publisher
    @DBRef
    private List<Book> books;



}
