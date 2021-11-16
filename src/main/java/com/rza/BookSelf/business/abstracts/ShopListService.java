package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.ShopList;

public interface ShopListService extends EntityService<ShopList> {
    ShopList getByBook(Book book);
}
