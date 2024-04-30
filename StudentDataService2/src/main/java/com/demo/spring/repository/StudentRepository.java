package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {

}
