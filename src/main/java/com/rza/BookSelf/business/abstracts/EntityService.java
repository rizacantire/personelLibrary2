package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.UsersBooks;

import java.util.List;

public interface EntityService<E> {
    List<E> getAlll();

    boolean add(E entity);

    boolean update(E entity);

    void delete(E entity);

    boolean deleteById(int id);
}
