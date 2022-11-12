package com.alexwork.persistance.repository;

import com.alexwork.persistance.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
