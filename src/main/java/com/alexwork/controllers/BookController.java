package com.alexwork.controllers;


import com.alexwork.persistance.dto.AuthorDto;
import com.alexwork.persistance.dto.BookDto;

import com.alexwork.persistance.model.Author;
import com.alexwork.persistance.model.Book;
import com.alexwork.services.AuthorService;
import com.alexwork.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bService;
    @Autowired
    private AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public ModelAndView home(@RequestParam(required = false) String sort, @RequestParam(required = false) String by) {
        logger.info(sort);
        logger.info(by);
        ModelAndView modelAndView = new ModelAndView("books");
        List<BookDto> listBook = bService.getAll(sort, by);
        modelAndView.addObject("listBook", listBook);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("by", by);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getBook(@PathVariable Long id) {
        ModelAndView modelAndView;
        try {
            BookDto bookDto = bService.getById(id);
            modelAndView = new ModelAndView("book");
            modelAndView.addObject("book", bookDto);
        } catch (Exception e) {
            logger.warn("Requested book with id='{}' is not exist", id, e);
            modelAndView = new ModelAndView("redirect:/book");
        }
        return modelAndView;
    }

    @PostMapping(value = "/add/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView addBook(BookDto book, @PathVariable Long id) {
        ModelAndView model;
        try {
            book = bService.save(book);
            AuthorDto author = authorService.getDtoById(id);
            Book bookNew = bService.getBookById(book.getId());
            author.setBook(bookNew);
            author = authorService.save(author);
        } catch (Exception e) {
            logger.warn("Created book {} is not successful", book, e);
        }
        return new ModelAndView("redirect:/books");
    }

    @GetMapping("/page/{id}")
    public ModelAndView page(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("create_book");
        BookDto bookDto = new BookDto();
        model.addObject("author_id", id);
        model.addObject("book", bookDto);
        return model;
    }

    @GetMapping("/page/{id}/{bookId}")
    public ModelAndView page(@PathVariable Long id, @PathVariable(required = false) Long bookId) {
        ModelAndView model = new ModelAndView("create_book");
        BookDto bookDto = new BookDto();
        if (bookId != null) {
            bookDto = bService.getById(bookId);
        }
        model.addObject("author_id", id);
        model.addObject("book", bookDto);
        return model;
    }

    @PostMapping("/update")
    public ModelAndView updateBook(@RequestBody Book book) {
        ModelAndView model;
        try {
            BookDto bookDto = bService.update(book);
            model = new ModelAndView("book");
            model.addObject("book", bookDto);
        } catch (Exception e) {
            logger.warn("Update book {} is not successful", book, e);
            model = new ModelAndView("redirect:/authors");
        }
        return model;
    }

    @GetMapping("/del/{id}")
    public ModelAndView deleteBook(@PathVariable Long id) {

        bService.delete(id);
        logger.warn("Delete book id = {}", id);
        return new ModelAndView("redirect:/books");
    }

}
