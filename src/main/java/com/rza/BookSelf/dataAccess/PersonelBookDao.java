package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonelBookDao extends JpaRepository<PersonelBook, Integer> {
    PersonelBook getPersonelBookByBook_Id(int bookId);
    Book findPersonelBookByBook_Id(int bookId);

}
