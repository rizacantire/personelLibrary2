package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonelBookDao extends JpaRepository<PersonelBook, Integer> {

}
