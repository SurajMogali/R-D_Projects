package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.Employee;

public interface EmpRepository extends MongoRepository<Employee, Integer>
{
	
}