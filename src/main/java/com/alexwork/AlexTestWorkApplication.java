package com.alexwork;

import com.alexwork.persistance.model.Author;
import com.alexwork.persistance.model.Book;
import com.alexwork.persistance.repository.AuthorRepository;
import com.alexwork.persistance.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.Embedded;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class AlexTestWorkApplication {
    @Autowired
    BookRepository bookRepo;

    public static void main(String[] args) {
        SpringApplication.run(AlexTestWorkApplication.class, args);
    }

    @Bean
    public CommandLineRunner testAppBook(@Autowired BookRepository bookRepo) {
        return args -> {
            bookRepo.save(
                    Book.builder()
                            .id(1L)
                            .title("Зеленая миля")
                            .publication(LocalDate.parse("1999-12-09"))
                            .type(Book.typeOfBook.BOOK)
                            .build());
            bookRepo.save(Book.builder().id(2L).title("Унесенные ветром").publication(LocalDate.parse("1936-06-30")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(3L).title("Крестный отец").publication(LocalDate.parse("1969-03-10")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(4L).title("Мастер и Маргарита").publication(LocalDate.parse("1966-11-11")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(5L).title("Над пропастью во ржи").publication(LocalDate.parse("1951-07-16")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(6L).title("Ведьмак").publication(LocalDate.parse("1986-05-15")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(7L).title("Три мушкетера").publication(LocalDate.parse("1844-07-01")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(8L).title("Белый клык").publication(LocalDate.parse("1906-10-01")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(9L).title("Град обреченный").publication(LocalDate.parse("1975-03-05")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(10L).title("Выбраковка").publication(LocalDate.parse("1999-01-01")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(11L).title("Хребты безумия").publication(LocalDate.parse("1936-02-01")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(12L).title("Изгоняющий дьявола").publication(LocalDate.parse("1971-08-17")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(13L).title("Кочегарка").publication(LocalDate.parse("2006-01-01")).type(Book.typeOfBook.BOOK).build());
            bookRepo.save(Book.builder().id(14L).title("Денискины рассказы").publication(LocalDate.parse("1959-02-12")).type(Book.typeOfBook.BOOK).build());
        };
    }

    @Bean
    public CommandLineRunner testAppAuthor(AuthorRepository authorRepo) {
        return args -> {
            authorRepo.save(Author.builder().id(1L).name("Стивен").surname("Кинг").book(bookRepo.getById(1L)).build());
            authorRepo.save(Author.builder().id(2L).name("Маргарет").surname("Митчел").book(bookRepo.getById(2L)).build());
            authorRepo.save(Author.builder().id(3L).name("Марио").surname("Пьюзо").book(bookRepo.getById(3L)).build());
            authorRepo.save(Author.builder().id(4L).name("Михаил").surname("Булгаков").book(bookRepo.getById(4L)).build());
            authorRepo.save(Author.builder().id(5L).name("Джером Дэвид").surname("Сэлиджер").book(bookRepo.getById(5L)).build());
            authorRepo.save(Author.builder().id(6L).name("Анджей").surname("Сапковкий").book(bookRepo.getById(6L)).build());
            authorRepo.save(Author.builder().id(7L).name("Александр").surname("Дюма").book(bookRepo.getById(7L)).build());
            authorRepo.save(Author.builder().id(8L).name("Джек").surname("Лондон").book(bookRepo.getById(8L)).build());
            authorRepo.save(Author.builder().id(9L).name("Аркидий и Борис").surname("Стругацкие").book(bookRepo.getById(9L)).build());
            authorRepo.save(Author.builder().id(10L).name("Олег").surname("Дивов").book(bookRepo.getById(10L)).build());
            authorRepo.save(Author.builder().id(11L).name("Говард").surname("Лавкрафт").book(bookRepo.getById(11L)).build());
            authorRepo.save(Author.builder().id(12L).name("Уильям").surname("Блэтти").book(bookRepo.getById(12L)).build());
            authorRepo.save(Author.builder().id(13L).name("Бентли").surname("Литтл").book(bookRepo.getById(13L)).build());
            authorRepo.save(Author.builder().id(14L).name("Виктор").surname("Драгунский").book(bookRepo.getById(14L)).build());

        };
    }
}
