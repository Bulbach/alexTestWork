package com.alexwork.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@ToString(exclude = "author")
@EqualsAndHashCode(exclude = "author")

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "publication")
    private LocalDate publication;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private typeOfBook type;

    @OneToOne(mappedBy = "book",fetch = FetchType.LAZY ,cascade = CascadeType.DETACH)
    private Author author;

    public enum typeOfBook{
        BOOK,
        MAGAZINE,
        NEWSPAPER
    }

}