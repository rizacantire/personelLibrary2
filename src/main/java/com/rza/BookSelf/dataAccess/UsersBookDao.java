package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.UsersBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBookDao extends JpaRepository<UsersBooks, Integer> {
    List<UsersBooks> findByCustomerUserId(int id);
}
