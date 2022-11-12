package com.alexwork.services;

import com.alexwork.persistance.repository.BookRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;
}
