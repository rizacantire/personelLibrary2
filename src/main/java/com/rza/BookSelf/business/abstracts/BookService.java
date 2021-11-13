package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.Author;
import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.Category;

import java.util.List;

public interface BookService extends EntityService<Book> {
    List<Book> getBookByCategory(List<Category> categoryName);
    List<Book> getByAuthor(Author author);
}
