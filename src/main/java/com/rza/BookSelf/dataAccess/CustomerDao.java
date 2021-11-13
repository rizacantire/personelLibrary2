package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
}
