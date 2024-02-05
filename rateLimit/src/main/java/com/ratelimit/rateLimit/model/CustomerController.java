package com.ratelimit.rateLimit.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(){
        return customerService.createCustomer();
    }

    @GetMapping
    public ResponseEntity<?> fetchCustomerById(@RequestHeader String id){
        return customerService.findCustomerById(id);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomerByName(@RequestHeader String id){
        return customerService.updateCustomer(id);
    }
}
