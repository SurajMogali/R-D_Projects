package com.demo.spring.repository;

import com.demo.spring.entity.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher,Integer> {
}
