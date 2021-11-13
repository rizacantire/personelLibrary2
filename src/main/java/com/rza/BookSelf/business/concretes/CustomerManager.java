package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.CustomerService;
import com.rza.BookSelf.dataAccess.CustomerDao;
import com.rza.BookSelf.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getAlll() {
        return this.customerDao.findAll();
    }

    @Override
    public boolean add(Customer entity) {
        this.customerDao.save(entity);
        return true;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
