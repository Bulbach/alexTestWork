package com.alexwork.persistance.repository;

import com.alexwork.persistance.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
