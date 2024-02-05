package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Book;
import com.demo.spring.repository.BookRepository;
import com.demo.spring.service.SequenceGeneratorService;

@RestController
public class BookController {
	
	@Autowired 
	private BookRepository bookRepository;
	
	@Autowired 
	private SequenceGeneratorService service;
	
	
	@PostMapping("/save")
	public Book addBook(@RequestBody Book book)
	{
		//generate sequence
		book.setId(service.getSequenceNumber(Book.SEQUENCE_NAME));
		return bookRepository.save(book);
	}
	
	@GetMapping("/getBooks")
	public List<Book> getAllbooks()
	{
		return bookRepository.findAll();
	}
	 

}
