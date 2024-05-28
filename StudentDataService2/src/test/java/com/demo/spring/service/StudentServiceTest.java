package com.demo.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.spring.entity.Student;
import com.demo.spring.repository.StudentRepository;

class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	private Student student;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		student = new Student();
		student.setStudentId(1);
		student.setFirstName("John");
		student.setLastName("Doe");
		student.setEmail("john@gmail.com");

	}

	@Test
	void testCreateStudent_AlreadyExists() {
		when(studentRepository.existsById(student.getStudentId())).thenReturn(true);

		ResponseEntity<String> response = studentService.createStudent(student);
		assertEquals("Student already exists", response.getBody());
	}

	@Test
	void testCreateStudent_Success() {
		when(studentRepository.existsById(student.getStudentId())).thenReturn(false);

		ResponseEntity<String> response = studentService.createStudent(student);
		assertEquals("Student saved successfully", response.getBody());
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void testGetStudent_Success() throws NotFoundException {
		when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));

		ResponseEntity<String> response = studentService.getStudent(student.getStudentId());
		assertEquals("Student data  fetched successfully", response.getBody());
	}

	@Test
	void testGetStudent_NotFound() throws NotFoundException {
		when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.empty());

		ResponseEntity<String> response = studentService.getStudent(student.getStudentId());
		assertEquals("Student data  not found", response.getBody());
	}

	@Test
	void testGetAllStudents() {
		List<Student> students = Arrays.asList(student);
		when(studentRepository.findAll()).thenReturn(students);

		ResponseEntity<List<Student>> response = studentService.getAllStudents();
		assertEquals(1, response.getBody().size());
	}

	@Test
	void testUpdateStudent_Success() throws NotFoundException {
		when(studentRepository.existsById(student.getStudentId())).thenReturn(true);

		ResponseEntity<String> response = studentService.updatedStudent(student);
		assertEquals("Student details updated successfully", response.getBody());
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void testUpdateStudent_NotFound() throws NotFoundException {
		when(studentRepository.existsById(student.getStudentId())).thenReturn(false);

		ResponseEntity<String> response = studentService.updatedStudent(student);
		assertEquals("No Such Student found", response.getBody());
	}

	@Test
	void testDeleteStudent_Success() {
		// Arrange
		when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));

		// Act
		ResponseEntity<String> response = studentService.deleteStudent(student.getStudentId());

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Student deleted", response.getBody());
		verify(studentRepository, times(1)).deleteById(student.getStudentId());
	}

	@Test
	void testDeleteStudent_NotFound() {
		// Arrange
		when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.empty());

		// Act
		ResponseEntity<String> response = studentService.deleteStudent(student.getStudentId());

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("No Such Student found", response.getBody());
		verify(studentRepository, never()).deleteById(student.getStudentId());
	}

}
