package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.Author;

public interface AuthorService extends EntityService<Author> {
    boolean isAuthorExist(Author author);

    Author findByAuthorName(String authorName, String authorLastName);

}
