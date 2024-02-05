package com.ratelimit.rateLimit.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    public ResponseEntity<?> createCustomer() {
        try {
            List<Customers> customersOptional = customerRepo.findAll();
            if (customersOptional.size() > 0){
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(customersOptional);
            }
            List<Customers> customersList = new ArrayList<>();
            Customers customers = new Customers();
            customers.setId(UUID.randomUUID().toString());
            customers.setFirstName("Suraj");
            customers.setLastName("M");
            customers.setEmail("suraj@email.com");
            customers.setPhoneNumber("7098654321");
            customersList.add(customers);
            Customers customers2 = new Customers();
            customers2.setId(UUID.randomUUID().toString());
            customers2.setFirstName("Thejashvi");
            customers2.setLastName("V");
            customers2.setPhoneNumber("8097654321");
            customers2.setEmail("thejashvi@email.com");
            customersList.add(customers2);
            Customers customers3 = new Customers();
            customers3.setId(UUID.randomUUID().toString());
            customers3.setFirstName("Honnur");
            customers3.setLastName("Ali");
            customers3.setPhoneNumber("9087654321");
            customers3.setEmail("honnur@email.com");
            customersList.add(customers3);
            Customers customers4 = new Customers();
            customers4.setId(UUID.randomUUID().toString());
            customers4.setFirstName("Bharath");
            customers4.setLastName("M");
            customers4.setPhoneNumber("1234567890");
            customers4.setEmail("bharath@email.com");
            customersList.add(customers4);
            customerRepo.saveAll(customersList);
            return ResponseEntity.ok().body(customersList);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> findCustomerById(String id) {
        try {
            Optional<Customers> customers = customerRepo.findById(id);
            if (customers.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.ok().body(customers);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateCustomer(String id) {
        try {
            Optional<Customers> customers = customerRepo.findById(id);
            if (customers.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            Customers customer = customers.get();
            customer.setPhoneNumber(customer.getPhoneNumber()+1);
            customerRepo.save(customer);

            return ResponseEntity.ok().body(customer);

        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
