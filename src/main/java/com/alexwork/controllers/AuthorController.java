package com.alexwork.controllers;

import com.alexwork.persistance.dto.AuthorDto;
import com.alexwork.persistance.model.Author;
import com.alexwork.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping
    public ModelAndView home(@RequestParam(required = false) String sort, @RequestParam(required = false) String by) {

       logger.info("method home(authors) was started");
        ModelAndView modelAndView = new ModelAndView("authors");
        List<AuthorDto> listAuthor = service.getAll(sort, by);
        modelAndView.addObject("listAuthor", listAuthor);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("by", by);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getAuthor(@PathVariable Long id) {

        ModelAndView modelAndView;
        try {
            AuthorDto authorDto = service.getDtoById(id);
            modelAndView = new ModelAndView("author");
            modelAndView.addObject("author", authorDto);
        } catch (Exception e) {
            logger.warn("Requested author with id='{}' is not exist", id, e);
            modelAndView = new ModelAndView("redirect:/authors");
        }

        return modelAndView;
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView addAuthor(AuthorDto author) {

        ModelAndView model;
        AuthorDto authorDto = new AuthorDto();
        boolean isAdd = author.getId() == null;
        try {
            authorDto = service.save(author);
        } catch (Exception e) {
            logger.warn("Created author {} is not successful", author, e);
        }
        if (isAdd) {
            model = new ModelAndView("redirect:/authors/" + authorDto.getId());

        } else {
            model = new ModelAndView("redirect:/authors");
        }

        return model;
    }

    @GetMapping("/page/{id}")
    public ModelAndView page(@PathVariable(required = false) Long id) {

        ModelAndView model = new ModelAndView("create_author");
        AuthorDto authorDto = new AuthorDto();
        if (id != null) {
            authorDto = service.getDtoById(id);
        }
        model.addObject("author", authorDto);

        return model;
    }
    @GetMapping("/page")
    public ModelAndView page(){

        ModelAndView model = new ModelAndView("create_author");
        AuthorDto authorDto = new AuthorDto();
        model.addObject("author", authorDto);

        return model;
    }

    @PostMapping("/update")
    public ModelAndView updateAuthor(@RequestBody Author author) {
        ModelAndView model;
        try {
            AuthorDto authorDto = service.update(author);
            model = new ModelAndView("author");
            model.addObject("author", authorDto);
        } catch (Exception e) {
            logger.warn("Update author {} is not successful", author, e);
            model = new ModelAndView("redirect:/authors");
        }
        return model;
    }

    @GetMapping("/del/{id}")
    public ModelAndView deleteAuthor(@PathVariable Long id) {

        service.delete(id);
        logger.warn("Delete aythor id = {}", id);
        return new ModelAndView("redirect:/authors");
    }

}
