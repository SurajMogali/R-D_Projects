package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmpRepository extends MongoRepository<Employee, Integer>
{
    @Query(value="{'empFirstName':?0}",fields="{'empLastName':0}")

    //fields="{'lastName':0}") - ignore this field
    //fields="{'lastName':1}") - show only this field
    List<Employee> findByName(String empFirstName);


	
}