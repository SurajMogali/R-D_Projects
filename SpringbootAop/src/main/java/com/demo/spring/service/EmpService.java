package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Employee;
import com.demo.spring.repository.EmpRepository;

@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository;


    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

   
}
