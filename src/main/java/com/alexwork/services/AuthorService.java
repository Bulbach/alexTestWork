package com.alexwork.services;

import com.alexwork.persistance.dto.AuthorDto;
import com.alexwork.persistance.mappers.AuthorMapper;
import com.alexwork.persistance.model.Author;
import com.alexwork.persistance.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDto> getAll(String order, String by) {

        List<Author> authors;
        Sort sort = getSort(order, by);
        authors = authorRepo.findAll(sort);

        if (authors.isEmpty()) {
            return Collections.emptyList();
        }

        return authors
                .stream()
                .map(author -> authorMapper.toDto(author))
                .collect(Collectors.toList());
    }

    private Sort getSort(String order, String by) {

        Sort orders;
        if (StringUtils.hasText(order) && StringUtils.hasText(by)) {
            orders = Sort.by(by);
            switch (order) {
                case "asc":
                    orders = orders.ascending();
                    break;
                case "desc":
                    orders = orders.descending();
                    break;
            }
        } else {
            orders = Sort.unsorted();
        }

        return orders;
    }

    public AuthorDto getDtoById(Long id) {

        Author author = authorRepo.getById(id);

        return authorMapper.toDto(author);
    }
    public Author getAuthorById(Long id){

        return authorRepo.getReferenceById(id);
    }

    public AuthorDto save(AuthorDto authorDto) {

        Author author = new Author();
        authorMapper.updateAuthorFromDto(authorDto, author);
        authorRepo.save(author);

        return authorMapper.toDto(author);
    }

    public AuthorDto update(Author author) {

        Author auth = authorRepo.getOne(author.getId());
        auth.setName(author.getName());
        auth.setSurname(author.getSurname());
        auth.setBook(author.getBook());
        authorRepo.save(auth);

        return authorMapper.toDto(auth);
    }

    public void delete(Long id) {

        authorRepo.deleteById(id);
    }
}
