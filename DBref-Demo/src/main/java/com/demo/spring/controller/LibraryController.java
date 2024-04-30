package com.demo.spring.controller;

import java.util.List;

import com.demo.spring.entity.Publisher;
import com.demo.spring.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Author;
import com.demo.spring.entity.Book;
import com.demo.spring.repository.AuthorRepository;
import com.demo.spring.repository.BookRepository;

import io.swagger.v3.oas.models.annotations.OpenAPI30;

@RestController
@RequestMapping("/api")
@OpenAPI30
public class LibraryController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    // Create an author
    @PostMapping("/authors/save")
    public Author createAuthor(@RequestBody Author author) {
    	Book book = bookRepository.save(author.getBook());
    	author.setBook(book);
        return authorRepository.save(author);
    }

    // Create a book
    @PostMapping("/books/save")
    public Book createBook(@RequestBody Book book) {
    
        return bookRepository.save(book);
    }

    // Read an author by ID
    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable String id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Read a book by ID
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Read all authors
    @GetMapping("/authors/getAll")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Read all books
    @GetMapping("/books/getAll")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Update an author
    @PutMapping("/authors/update/{id}")
    public Author updateAuthor(@PathVariable String id, @RequestBody Author author) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setName(author.getName());
            // Update any other fields if needed

            Book book = bookRepository.save(author.getBook());
            author.setBook(book);

            return authorRepository.save(existingAuthor);
        }
        return null; // Return appropriate response if author not found
    }

    // Update a book
    @PutMapping("/books/update/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setGenre(book.getGenre());
            // Update any other fields if needed
//          Author author =new Author();
//           author.setBook(existingBook);
            return bookRepository.save(existingBook);



        }
        return null; // Return appropriate response if book not found
    }

    // Delete an author
    @DeleteMapping("/authors/delete/{id}")
    public void deleteAuthor(@PathVariable String id) {
        authorRepository.deleteById(id);
    }

    // Delete a book
    @DeleteMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
    }



    @PostMapping("/publisher/save")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        // Save the Publisher
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Save each Book associated with the Publisher
        if (publisher.getBooks() != null) {
            for (Book book : publisher.getBooks()) {
                // Set the Publisher reference for each Book
                book.setPublisher(savedPublisher);
                bookRepository.save(book);
            }
        }

        return savedPublisher;
    }




        // Save the Publisher















}
