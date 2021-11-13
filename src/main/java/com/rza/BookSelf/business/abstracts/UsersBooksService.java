package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.UsersBooks;

import java.util.List;

public interface UsersBooksService extends EntityService<UsersBooks> {
    void addBooks(UsersBooks usersBooks);

    List<UsersBooks> getByUserId(int id);
}
