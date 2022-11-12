package com.alexwork.persistance.dto;

import com.alexwork.persistance.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Data
@NoArgsConstructor
@Component
public class AuthorDto {

    private Long id;
    private String name;
    private String surname;
    private Book book;
}
