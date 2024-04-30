package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Book;
import com.demo.spring.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public ResponseEntity<String> addBook(Book book) {
		if (bookRepository.existsById(book.getId())) {
			return ResponseEntity.ok("Book Already exists");
		} else {
			bookRepository.save(book);
			return ResponseEntity.ok("Book added");
		}
	}

	public Optional<Book> getBook(Integer id) {
		Optional<Book> bookOp = bookRepository.findById(id);
		if (bookOp.isPresent()) {
			return bookOp;

		} else {
			return null;
		}

	}

	public Book updateBook(Book book) {
		if (bookRepository.existsById(book.getId())) {
			return bookRepository.save(book);
		} else
			return null;

	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();

	}

	public void deleteBookbyId(Integer id) {
		Optional<Book> bookOp = bookRepository.findById(id);
		if (bookOp.isPresent()) {
			bookRepository.deleteById(id);

		}
	}

	public Book updateLastName(Integer id, String lastName) {

		Optional<Book> bookOp = bookRepository.findById(id);
		if (bookOp.isPresent()) {
			Book book = bookOp.get();
			book.setAuthorLastName(lastName);
			return bookRepository.save(book);
		} else

		{
			return null;
		}

	}
}
