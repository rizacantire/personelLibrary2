package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBookDao extends JpaRepository<FavoriteBook,Integer> {
}
