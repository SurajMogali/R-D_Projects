package com.demo.spring.controller;


import com.demo.spring.entity.Person;
import com.demo.spring.repository.PersonRepositroy;
import com.demo.spring.request.AddPersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PersonController {

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    PersonRepositroy personRepositroy;

    @PostMapping("/person/save")
    public String savePerson(@RequestBody AddPersonRequest request)
    {
        Person person = personRepositroy.findByName(request.getName()+" "+0);
        if(person != null)
        {
            return "Person Already exists";

        }

        for(int i=0;i<50;i++)
        {
            person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setName(request.getName()+" "+i);
            person.setAddress(request.getAddress()+" "+i);
            personRepositroy.save(person);

        }


        return "Persons are saved";
    }

    @GetMapping("/persons")
    public List<Person> getPersonsPerPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
    	
    	//pagesize=no of documents per page
    	//pageNumber=current page number
        AggregationOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        AggregationOperation limitOperation = Aggregation.limit(pageSize);

        // Aggregation
        Aggregation aggregation = Aggregation.newAggregation(
        		
                skipOperation,
                limitOperation
        );

        AggregationResults<Person> output = mongoTemplate.aggregate(aggregation, "person", Person.class);
        return output.getMappedResults();
    }






}
