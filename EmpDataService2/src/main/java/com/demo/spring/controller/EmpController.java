package com.demo.spring.controller;

import java.util.List;
import java.util.Optional;

import com.demo.spring.entity.EmpCount;
import com.demo.spring.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.spring.entity.Employee;
import com.demo.spring.service.EmpService;
import org.webjars.NotFoundException;

import io.swagger.v3.oas.models.annotations.OpenAPI30;



@RestController
@RequestMapping(path = "/employees")
@OpenAPI30
public class EmpController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    EmpRepository empRepository;

    @Autowired
    private EmpService empService;

    @PostMapping("/save")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = empService.createEmployee(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllEmployees() {
        try {
            List<Employee> employees = empService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve employees: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{empId}")
	public Optional<Employee> getEmployeeById(@PathVariable Integer empId) {
		return empService.getEmployeeById(empId);
	}

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee updatedEmployee) {
        try {
            Employee updated = empService.updatedEmployee(updatedEmployee);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Employee not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer empId) {
        try {
            empService.deleteEmployee(empId);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Employee not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listByfName/{empFirstName}")
    public List<Employee> findAll(@PathVariable String empFirstName)
    {
//        Query query =new Query();
//        query.addCriteria(Criteria.where("empFirstName").is(empFirstName));
//        return mongoTemplate.find(query,Employee.class);
          return empRepository.findByName(empFirstName);



    }

    @GetMapping("/byLastName/{empLastName}")
    List<Employee> findByLastName(@PathVariable String empLastName)
    {


        //MatchOperation
        MatchOperation matchOperation= Aggregation.match(new Criteria("empLastName").is(empLastName));


        //SortOperation
        SortOperation sortOperation=Aggregation.sort(Sort.by(Sort.Direction.DESC,"empLastName"));

        //Aggregation
        Aggregation aggregation=Aggregation.newAggregation(matchOperation,sortOperation);

        AggregationResults output=mongoTemplate.aggregate(aggregation,"employee", Employee.class);
        return output.getMappedResults();

    }


    @GetMapping("/byLastNameCount/{empLastName}")
    List<Employee> findByLastNameCount(@PathVariable String empLastName)
    {
        //MatchOperation
        MatchOperation matchOperation= Aggregation.match(new Criteria("empLastName").is(empLastName));

        //Group
        GroupOperation groupOperation=Aggregation.group().count().as("count");

        //SortOperation
        SortOperation sortOperation=Aggregation.sort(Sort.by(Sort.Direction.DESC,"empLastName"));

        //Aggregation
        Aggregation aggregation=Aggregation.newAggregation(matchOperation,groupOperation);

        AggregationResults output=mongoTemplate.aggregate(aggregation,"employee", EmpCount.class);
        return output.getMappedResults();

    }

    @GetMapping("/allAgesOperations")
    List<Employee> findAllAgesOperations() {
        // Group
        GroupOperation groupOperation = Aggregation.group()
                .sum("age").as("sum")
                .max("age").as("maxAge")
                .min("age").as("minAge");

        // Aggregation
        Aggregation aggregation = Aggregation.newAggregation(groupOperation);

        AggregationResults output = mongoTemplate.aggregate(aggregation, "employee", EmpCount.class);
        return output.getMappedResults();
    }

}
