package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.PersonelBookService;
import com.rza.BookSelf.dataAccess.PersonelBookDao;
import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonelBookManager implements PersonelBookService {

    @Autowired
    private PersonelBookDao personelBookDao;

    public PersonelBookManager(PersonelBookDao personelBookDao) {
        this.personelBookDao = personelBookDao;
    }

    @Override
    public List<PersonelBook> getAlll() {
        return this.personelBookDao.findAll();
    }

    @Override
    public boolean add(PersonelBook entity) {
        this.personelBookDao.save(entity);
        return true;
    }

    @Override
    public boolean update(PersonelBook entity) {
        this.personelBookDao.save(entity);
        return true;

    }

    @Override
    public void delete(PersonelBook entity) {
        this.personelBookDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {
        this.personelBookDao.deleteById(id);
        return true;
    }

    @Override
    public PersonelBook getById(int id) {
        return this.personelBookDao.getById(id);
    }

    @Override
    public PersonelBook getByBookId(int id) {
        return this.personelBookDao.getPersonelBookByBook_Id(id);
    }

    @Override
    public Book getBookByBookId(int bookId) {
        return this.personelBookDao.findPersonelBookByBook_Id(bookId);
    }


}
