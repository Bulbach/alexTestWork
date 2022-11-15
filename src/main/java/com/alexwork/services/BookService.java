package com.alexwork.services;

import com.alexwork.persistance.dto.BookDto;
import com.alexwork.persistance.mappers.BookMapper;
import com.alexwork.persistance.model.Book;
import com.alexwork.persistance.repository.BookRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private BookMapper bookMapper;


    public List<BookDto> getAll(String order, String by) {
        List<Book> books;
        Sort sort = getSort(order, by);
        books = bookRepo.findAll(sort);

        if (books.isEmpty()) {
            return Collections.emptyList();
        }

        return books
                .stream()
                .map(book -> bookMapper.toDto(book))
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

    public BookDto getById(Long id) {

        Book book = bookRepo.getById(id);

        return bookMapper.toDto(book);
    }


    public BookDto save(BookDto bookDto) {

        Book bookNew = new Book();
        bookMapper.updateBookFromDto(bookDto,bookNew);
        bookRepo.save(bookNew);

        return bookMapper.toDto(bookNew);
    }

    public BookDto update(Book book) {

        Book bookUp = bookRepo.getById(book.getId());
        bookUp = Book.builder()
                .title(book.getTitle())
                .publication(book.getPublication())
                .author(book.getAuthor())
                .type(book.getType())
                .build();
        bookRepo.save(bookUp);

        return bookMapper.toDto(bookUp);
    }

    public void delete(Long id) {bookRepo.deleteById(id);

    }


    public Book getBookById(Long id) {
        return bookRepo.getReferenceById(id);
    }
}
