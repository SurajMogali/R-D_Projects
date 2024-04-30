package com.demo.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.Customer;


@Repository
public interface CustomerDTO extends CrudRepository<Customer, Integer>{

}
