package com.javatechie.spring.mongo.embeded.api.repository;



import com.javatechie.spring.mongo.embeded.api.model.Address;
import com.javatechie.spring.mongo.embeded.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, Integer>{



}
