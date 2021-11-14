package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.FavoriteBook;
import com.rza.BookSelf.entities.concretes.PersonelBook;

public interface FavoriteBookService extends EntityService<FavoriteBook> {
    FavoriteBook getByPersonelBook(PersonelBook personelBook);
}
