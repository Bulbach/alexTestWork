package com.alexwork.persistance.mappers;

import com.alexwork.persistance.dto.BookDto;
import com.alexwork.persistance.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring",uses = AuthorMapper.class)
public interface BookMapper {

    BookDto toDto(Book book);
    Book toModel(BookDto bookDto);
    void updateBookFromDto(BookDto dto, @MappingTarget Book book);

}
