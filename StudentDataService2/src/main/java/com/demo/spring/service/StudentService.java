package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Student;
import com.demo.spring.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public ResponseEntity<String> createStudent(Student s) {
		if (studentRepository.existsById(s.getStudentId())) {
			return ResponseEntity.ok("Student already exists");

		} else

		{
			studentRepository.save(s);
			return ResponseEntity.ok("Student saved successfully");

		}

	}

	public ResponseEntity<String> getStudent(Integer studentId) throws NotFoundException {
		// System.out.println(studentRepository.findById(studentId));
		Optional<Student> studentOp = studentRepository.findById(studentId);
		if (studentOp.isPresent()) {
			studentOp.get();
			return ResponseEntity.ok("Student data  fetched successfully");

		} else

		{
			return ResponseEntity.ok("Student data  not found");

		}

	}

	public ResponseEntity<List<Student>> getAllStudents() {

		return ResponseEntity.ok(studentRepository.findAll());
	}

	public ResponseEntity<String> updatedStudent(Student updatedStudent) throws NotFoundException {
		if (studentRepository.existsById(updatedStudent.getStudentId())) {

			studentRepository.save(updatedStudent);
			return ResponseEntity.ok("Student details updated successfully");

		} else

		{
			return ResponseEntity.ok("No Such Student found");
		}

	}

	public ResponseEntity<String> deleteStudent(Integer studentId) {
		Optional<Student> studOp=studentRepository.findById(studentId);
		if(studOp.isPresent())
		{
			studentRepository.deleteById(studentId);
			return ResponseEntity.ok("Student deleted");
			
		}
		else
		{
			
			return ResponseEntity.ok("No Such Student found");
		
		}

		
		
	}
}
