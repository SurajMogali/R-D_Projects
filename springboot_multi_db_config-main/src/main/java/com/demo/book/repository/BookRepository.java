package com.demo.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
