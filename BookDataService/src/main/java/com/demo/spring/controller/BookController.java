package com.demo.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Book;
import com.demo.spring.service.BookService;

import io.swagger.v3.oas.models.annotations.OpenAPI30;

@RestController
@RequestMapping("/book")
@OpenAPI30
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/getBook/{id}")
	public Optional<Book> getBook(@PathVariable Integer id) {
		return bookService.getBook(id);
	}

	@PostMapping("/save")
	public ResponseEntity<String> createBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@DeleteMapping("/deleteBook/{id}")
	public void deleteBook(@PathVariable Integer id) {
		bookService.deleteBookbyId(id);

	}

	@PatchMapping("/updateLastName/{id}")
	public Book updateLastName(@PathVariable Integer id, String lastName) {
		return bookService.updateLastName(id, lastName);

	}
}
