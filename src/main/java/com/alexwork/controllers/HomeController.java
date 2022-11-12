package com.alexwork.controllers;


import com.alexwork.persistance.repository.AuthorRepository;
import com.alexwork.persistance.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("start")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }

}