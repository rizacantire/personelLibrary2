package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.AuthorService;
import com.rza.BookSelf.dataAccess.AuthorDao;
import com.rza.BookSelf.entities.concretes.Author;
import com.rza.BookSelf.entities.concretes.UsersBooks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {
    private AuthorDao authorDao;

    public AuthorManager(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getAlll() {
        return this.authorDao.findAll();
    }

    @Override
    public boolean add(Author entity) {
        if (isAuthorExist(entity)) {
            this.authorDao.save(entity);
            System.out.println("Eklendi");
            return true;
        } else {
            System.out.println("Sistemde Mevcut");
            return false;
        }
    }

    @Override
    public boolean update(Author entity) {
        this.authorDao.save(entity);
        return true;
    }

    @Override
    public void delete(Author entity) {
        this.authorDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {
        this.authorDao.deleteById(id);
        return true;
    }

    @Override
    public boolean isAuthorExist(Author author) {
        var result = this.authorDao.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (result == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByAuthorName(String authorName, String authorLastName) {
        return this.authorDao.findByFirstNameAndLastName(authorName, authorLastName);
    }

    @Override
    public Author findById(int id) {
        return this.authorDao.findById(id);
    }
}
