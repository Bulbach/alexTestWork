package com.alexwork.persistance.mappers;

import com.alexwork.persistance.dto.AuthorDto;
import com.alexwork.persistance.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring",uses = BookMapper.class)
public interface AuthorMapper {

    AuthorDto toDto(Author author);
    Author toModel(AuthorDto dto);
    void updateAuthorFromDto(AuthorDto dto, @MappingTarget Author author);

}
