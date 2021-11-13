package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer> {
    Author findByFirstNameAndLastName(String name, String lastName);
}
