package com.rza.BookSelf.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int page;

    @ManyToMany
    private List<Author> authors;

    @ManyToMany
    private List<Category> categories;

    @OneToMany
    private List<UsersBooks> usersBooks;

    @OneToMany
    private List<PersonelBook> personelBooks;

    @ManyToOne
    private ShopList shopList;

}
