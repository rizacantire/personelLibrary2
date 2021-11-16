package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.PersonelBook;

public interface PersonelBookService extends EntityService<PersonelBook> {
    PersonelBook getById(int id);

    PersonelBook getByBookId(int id);

    Book getBookByBookId(int bookId);
}
