package com.rza.BookSelf.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String firstName;
    private String lastName;
    //private Date birthDate;

    @ManyToMany
    private List<Book> books;

    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
