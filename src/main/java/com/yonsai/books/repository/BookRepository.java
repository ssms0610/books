package com.yonsai.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonsai.books.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
