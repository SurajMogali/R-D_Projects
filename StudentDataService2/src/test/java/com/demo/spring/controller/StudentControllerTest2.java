package com.demo.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.spring.entity.Student;
import com.demo.spring.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

class StudentControllerTest2 {

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
    void testDeleteStudent() throws Exception {
        int studentId = 1;
        when(studentService.deleteStudent(studentId)).thenReturn(ResponseEntity.ok("Student deleted"));

        MockHttpServletRequestBuilder reqBuilder = delete("/student/deleteStudent/{studentId}", studentId)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertEquals("Student deleted", responseContent);
    }

    @Test
    void testDeleteStudent_Failure() throws Exception {
        int studentId = 2;
        when(studentService.deleteStudent(studentId)).thenReturn(ResponseEntity.ok("No Such Student found"));

        MockHttpServletRequestBuilder reqBuilder = delete("/student/deleteStudent/{studentId}", studentId)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertEquals("No Such Student found", responseContent);
    }
    
    
    @Test
	public void testCreateEmployee2() throws Exception {

		// define mock obj behaviour
		when(studentService.createStudent(any(Student.class))).thenReturn(ResponseEntity.ok("Student saved successfully"));

		// Serialize Employee object to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(student);

		// prepare http get request
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/student/saveStudent")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody);

		// send request and hold response
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		// validate response
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
		
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertEquals("Student saved successfully", responseContent);

	}
    
    
    
}
