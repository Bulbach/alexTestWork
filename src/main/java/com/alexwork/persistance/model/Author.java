package com.alexwork.persistance.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "book")
@EqualsAndHashCode(exclude = "book")

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "books_id", referencedColumnName = "id")
    private Book book;
}