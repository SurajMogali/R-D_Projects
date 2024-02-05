package com.demo.spring.processor;

import org.springframework.batch.item.ItemProcessor;

import com.demo.spring.entity.Student;

public class StuProcessor implements ItemProcessor<Student,Student> {
	
	@Override
	public Student process(Student item) throws Exception
	{
		
		final String firstName=item.getFirstName().toUpperCase();
		final String lastName=item.getLastName().toUpperCase();
		Student s =new Student(item.getId(),firstName,lastName,item.getEmail());
		return s;
		
	}

}
