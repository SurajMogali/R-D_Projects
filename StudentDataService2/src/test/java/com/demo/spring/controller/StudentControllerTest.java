package com.demo.spring.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.spring.entity.Student;
import com.demo.spring.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;


//squ_ce88c2e32964217d3ebb70c6e56b98a4717d70d5
class StudentControllerTest {

	private MockMvc mockMvc;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build(); 
        student = new Student();
        student.setStudentId(1);
        student.setFirstName("suraj");
        student.setLastName("mogali");
        student.setEmail("suraj@gmail.com");
        
        
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(ResponseEntity.ok("Student saved successfully"));

        mockMvc.perform(post("/student/saveStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student saved successfully"));
    }

    @Test
    void testCreateStudent_Failure() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save student"));

        mockMvc.perform(post("/student/saveStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to save student"));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudent(student.getStudentId())).thenReturn(ResponseEntity.ok("Student data fetched successfully"));

        mockMvc.perform(get("/student/getStudent/{studentId}", student.getStudentId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Student data fetched successfully"));
    }

    @Test
    void testGetStudentById_NotFound() throws Exception {
        when(studentService.getStudent(student.getStudentId())).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student data not found"));

        mockMvc.perform(get("/student/getStudent/{studentId}", student.getStudentId()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Student data not found"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(ResponseEntity.ok(students));

        mockMvc.perform(get("/student/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentId").value(student.getStudentId()))
                .andExpect(jsonPath("$[0].firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(student.getLastName()))
                .andExpect(jsonPath("$[0].email").value(student.getEmail()));
        
        
    }

    @Test
    void testGetAllStudents_Empty() throws Exception {
        when(studentService.getAllStudents()).thenReturn(ResponseEntity.ok(Arrays.asList()));

        mockMvc.perform(get("/student/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.updatedStudent(any(Student.class))).thenReturn(ResponseEntity.ok("Student details updated successfully"));

        mockMvc.perform(put("/student/updateStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student details updated successfully"));
    }

    @Test
    void testUpdateStudent_NotFound() throws Exception {
        when(studentService.updatedStudent(any(Student.class))).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Student found"));

        mockMvc.perform(put("/student/updateStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Such Student found"));
    }
    
    
    @Test
    void testDeleteStudent_Success() throws Exception {
        when(studentService.deleteStudent(student.getStudentId())).thenReturn(ResponseEntity.ok("Student deleted"));

        mockMvc.perform(delete("/student/deleteStudent/{studentId}", student.getStudentId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted"));
    }

    @Test
    void testDeleteStudent_NotFound() throws Exception {
        when(studentService.deleteStudent(student.getStudentId())).thenReturn(ResponseEntity.ok("No Such Student found"));

        mockMvc.perform(delete("/student/deleteStudent/{studentId}", student.getStudentId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("No Such Student found"));
    }
    
    
    
    
   
}

