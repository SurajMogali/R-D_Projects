package com.demo.spring.repository;

import com.demo.spring.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepositroy extends MongoRepository<Person,String> {


    boolean existsByName();

    Person findByName(String name);
}
