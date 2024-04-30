package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.demo.spring.entity.Employee;
import com.demo.spring.repository.EmpRepository;

@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository;

    public Employee createEmployee(Employee e) {
        return empRepository.save(e);
    }

    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer empId) {
        return empRepository.findById(empId);
    }

    public Employee updatedEmployee(Employee updatedEmployee) throws NotFoundException {
        if (empRepository.existsById(updatedEmployee.getEmpId())) {
            return empRepository.save(updatedEmployee);
        } else {
            throw new NotFoundException("Employee not found with ID: " + updatedEmployee.getEmpId());
        }
    }

    public void deleteEmployee(Integer empId) throws NotFoundException {
        if (empRepository.existsById(empId)) {
            empRepository.deleteById(empId);
        } else {
            throw new NotFoundException("Employee not found with ID: " + empId);
        }
    }
}
