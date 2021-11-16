package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopListDao extends JpaRepository<ShopList,Integer> {
    ShopList findByBook(Book book);
}
