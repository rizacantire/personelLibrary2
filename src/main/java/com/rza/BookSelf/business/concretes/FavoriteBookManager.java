package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.FavoriteBookService;
import com.rza.BookSelf.dataAccess.FavoriteBookDao;
import com.rza.BookSelf.entities.concretes.FavoriteBook;
import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteBookManager implements FavoriteBookService {

    @Autowired
    private FavoriteBookDao favoriteBookDao;

    public FavoriteBookManager(FavoriteBookDao favoriteBookDao) {
        this.favoriteBookDao = favoriteBookDao;
    }

    @Override
    public List<FavoriteBook> getAlll() {
        return this.favoriteBookDao.findAll();
    }

    @Override
    public boolean add(FavoriteBook entity) {
        this.favoriteBookDao.save(entity);
        return true;
    }

    @Override
    public boolean update(FavoriteBook entity) {
        this.favoriteBookDao.save(entity);
        return true;
    }

    @Override
    public void delete(FavoriteBook entity) {
        this.favoriteBookDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {
        this.favoriteBookDao.deleteById(id);
        return true;
    }

    @Override
    public FavoriteBook getByPersonelBook(PersonelBook personelBook) {
        return this.favoriteBookDao.getFavoriteBookByPersonelBooks(personelBook);
    }
}
