package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Author;
import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> findBookByName(String name);

    List<Book> findBooksByCategoriesIn(List<Category> categoryName);

    List<Book> getBookByAuthors(Author author);

}
