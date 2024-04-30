package com.demo.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Student;
import com.demo.spring.service.StudentService;

import io.swagger.v3.oas.models.annotations.OpenAPI30;

@RestController
@RequestMapping("/student")
@OpenAPI30
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(path = "/saveStudent")
	public ResponseEntity<String> createStudent(@RequestBody Student s) {
		return studentService.createStudent(s);
	}

	@GetMapping(path = "/getStudent/{studentId}")
	public ResponseEntity<String> getStudentbyId(@PathVariable Integer studentId) throws NotFoundException  {
		//System.out.println(studentService.getStudentbyId(studentId));
		
		return studentService.getStudent(studentId);
	}

	@GetMapping(path = "/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		return studentService.getAllStudents();
	}

	@PutMapping(path = "/updateStudent")
	public ResponseEntity<String> updatedStudent(@RequestBody Student updatedStudent) throws NotFoundException {
		return studentService.updatedStudent(updatedStudent);

	}

	@DeleteMapping(path = "/deleteStudent/{studentId}") 
	public void deleteStudent(@PathVariable Integer studentId) throws NotFoundException {
		studentService.deleteStudent(studentId);
	}

}
