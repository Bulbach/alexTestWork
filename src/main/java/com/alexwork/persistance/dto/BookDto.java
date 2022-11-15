package com.alexwork.persistance.dto;

import com.alexwork.persistance.model.Author;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Component
public class BookDto {
    private Long id;
    private String title;
    private String publication;
    private String type;
    private Author author;

}
