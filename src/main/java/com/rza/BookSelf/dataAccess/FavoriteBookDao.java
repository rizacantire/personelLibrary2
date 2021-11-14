package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.FavoriteBook;
import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBookDao extends JpaRepository<FavoriteBook, Integer> {
    FavoriteBook getFavoriteBookByPersonelBooks(PersonelBook personelBook);

}
